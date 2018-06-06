package com.todarch.gw.jwt;

import com.todarch.gw.common.GwPostFilter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class LogoutPostFilter extends GwPostFilter {

  private final JwtUtil jwtUtil;

  private static final String PARTIAL_LOGOUT_ENDPOINT = "logout";

  @Override
  public boolean shouldFilter() {
    return isLogoutEndpoint();
  }

  private boolean isLogoutEndpoint() {
    String requestUri = request().getRequestURI();
    if (requestUri != null) {
      log.info("Is logging endpoint: {}", requestUri);
      return requestUri.toLowerCase().contains(PARTIAL_LOGOUT_ENDPOINT);
    }
    log.info("Was not logout endpoint");
    return false;
  }

  @Override
  public Object run() {
    response().addCookie(jwtUtil.getLogoutCookie());
    log.info("Set logout cookie");
    return null;
  }
}
