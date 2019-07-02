package com.demo.editcommentdialog;

import android.app.Application;
import android.content.Context;

public class BaseApp extends Application{
  protected static BaseApp APP = null;

  /** 红包活动开关是否开启，服务端控制 */
  private boolean redMoneyActivityOpen;

  public static BaseApp getApp() {
    return APP;
  }

  @Override
  public void onCreate() {
    APP = this;
    super.onCreate();
  }
  public static BaseApp get() {
    return APP;
  }

  public Context getContext() {
    return this;
  }
}
