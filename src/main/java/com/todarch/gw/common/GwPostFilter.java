package com.todarch.gw.common;

public abstract class GwPostFilter extends GwFilter {
  @Override
  public String filterType() {
    return "post";
  }
}
