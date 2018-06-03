package com.todarch.gw.log;

import com.todarch.gw.common.GwPreFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class RequestLogPreFilter extends GwPreFilter {

  @Override
  public Object run() {
    HttpServletRequest request = request();
    log.info("{} request to {}", request.getMethod(), request.getRequestURL().toString());
    return null;
  }
}
