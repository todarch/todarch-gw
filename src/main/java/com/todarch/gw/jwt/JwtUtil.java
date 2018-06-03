package com.todarch.gw.jwt;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
  static final String JWT_HEADER_NAME = "Authorization";
  private static final String JWT_PREFIX = "Bearer ";
  private static final String JWT_COOKIE_NAME = "todarch-auth";
  private static final long JWT_COOKIE_LIVE = 8L;

  Cookie getJwtCookie(String jwt) {
    // An invalid character [32] was present in the Cookie value
    jwt = jwt.substring(JWT_PREFIX.length(), jwt.length());
    Cookie authCookie = new Cookie(JWT_COOKIE_NAME, jwt);
    authCookie.setHttpOnly(true);
    authCookie.setMaxAge((int) TimeUnit.SECONDS.convert(JWT_COOKIE_LIVE, TimeUnit.HOURS));
    authCookie.setPath("/");
    return authCookie;
  }

  Optional<String> resolveToken(HttpServletResponse response) {
    String bearerToken = response.getHeader(JWT_HEADER_NAME);
    return doResolveToken(bearerToken);
  }

  Optional<String> resolveToken(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    // at the beginning there will not be any cookies
    if (cookies != null) {
      return Arrays.stream(cookies)
          .filter(cookie -> JWT_COOKIE_NAME.equals(cookie.getName()))
          .map(Cookie::getValue)
          .findAny();
    }
    return Optional.empty();
  }

  private Optional<String> doResolveToken(String bearerToken) {
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JWT_PREFIX)) {
      String token = bearerToken.substring(JWT_PREFIX.length(), bearerToken.length());
      return Optional.of(token);
    }
    return Optional.empty();
  }

  public String getJwtHeader(String jwt) {
    return JWT_PREFIX + jwt;
  }
}
