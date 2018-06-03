package com.todarch.gw.common;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class GwFilter extends ZuulFilter {

  @Override
  public int filterOrder() {
    return 0;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  protected HttpServletRequest request() {
    return RequestContext.getCurrentContext().getRequest();
  }

  protected HttpServletResponse response() {
    return RequestContext.getCurrentContext().getResponse();
  }

  protected RequestContext context() {
    return RequestContext.getCurrentContext();
  }
}
