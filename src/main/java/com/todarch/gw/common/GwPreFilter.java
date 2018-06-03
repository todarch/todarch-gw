package com.todarch.gw.common;

public abstract class GwPreFilter extends GwFilter {
  @Override
  public String filterType() {
    return "pre";
  }
}
