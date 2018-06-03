package com.todarch.gw.jwt;

import com.netflix.util.Pair;
import com.todarch.gw.common.GwPostFilter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Extracts jwt from header, (encrypt), and puts in a cookie.
 */
@Component
@AllArgsConstructor
@Slf4j
public class JwtPostFilter extends GwPostFilter {

  private final JwtUtil jwtUtil;

  @Override
  public Object run() {
    // at this point there is no real response,
    // zuul wrapper will be applied to create final response
    // SendResponseFilter
    //TODO:selimssevgi: com.netflix.util.Pair not found
    Optional<String> optionalJwt = extractAuthHeaderFromZuulResponse();
    HttpServletResponse response = response();
    // Optional<String> optionalJwt = jwtUtil.resolveToken(response);

    // response.addCookie(jwtUtil.getJwtCookie("dummy"));

    if (optionalJwt.isPresent()) {
      log.info("Found jwt in response header");
      String jwt = optionalJwt.get();
      // encrypt jwt
      response.addCookie(jwtUtil.getJwtCookie(jwt));
      log.info("Added auth cookie");
    } else {
      log.info("Did not find jwt in response header. no-op");
    }
    return null;
  }

  private Optional<String> extractAuthHeaderFromZuulResponse() {
    return context().getZuulResponseHeaders().stream()
        .filter(pair -> JwtUtil.JWT_HEADER_NAME.equalsIgnoreCase(pair.first()))
        .map(Pair::second)
        .findAny();
  }
}
