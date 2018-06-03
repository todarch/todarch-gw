package com.todarch.gw.jwt;

import com.todarch.gw.common.GwPreFilter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class JwtPreFilter extends GwPreFilter {

  private final JwtUtil jwtUtil;

  @Override
  public Object run() {
    log.info("In jwtPreFilter");
    HttpServletRequest request = request();
    Optional<String> optionalJwt = jwtUtil.resolveToken(request);

    if (optionalJwt.isPresent()) {
      String jwt = optionalJwt.get();
      log.info("Found jwt in request");
      // decrypt
      context().addZuulRequestHeader(JwtUtil.JWT_HEADER_NAME, jwtUtil.getJwtHeader(jwt));
    } else {
      log.info("Request to {} does not have auth cookie", request.getRequestURI());
    }
    return null;
  }
}
