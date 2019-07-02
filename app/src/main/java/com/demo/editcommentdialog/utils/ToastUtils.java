package com.demo.editcommentdialog.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.demo.editcommentdialog.BaseApp;
import com.demo.editcommentdialog.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ToastUtils {

    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void showToast(int resID) {

        //		showToast(VideoApplication.getContext(), Toast.LENGTH_SHORT, resID);
        try {
            showToast(BaseApp.get(), Toast.LENGTH_LONG, resID);
        } catch (Exception e) {

        }
    }

    public static void showToast(String text) {
        try {
            showToast(BaseApp.get(), Toast.LENGTH_LONG, text);
            //		showToastErrorTip(JsonUtils.getJsonMsg(text));
        } catch (Exception e) {

        }
    }

    public static void showToast(Context ctx, int resID) {
        try {
            showToast(ctx, Toast.LENGTH_LONG, resID);
        } catch (Exception e) {

        }
    }

    public static void showToast(Context ctx, String text) {
        try {
            showToast(ctx, Toast.LENGTH_LONG, text);
        } catch (Exception e) {

        }
    }

    public static void showLongToast(Context ctx, int resID) {
        try {
            showToast(ctx, Toast.LENGTH_LONG, resID);
        } catch (Exception e) {

        }
    }

    public static void showLongToast(int resID) {
        try {
            showToast(BaseApp.get(), Toast.LENGTH_LONG, resID);
        } catch (Exception e) {

        }
    }

    public static void showLongToast(Context ctx, String text) {
        try {
            showToast(ctx, Toast.LENGTH_LONG, text);
        } catch (Exception e) {

        }
    }

    public static void showLongToast(String text) {
        try {
            showToast(BaseApp.get(), Toast.LENGTH_LONG, text);
        } catch (Exception e) {

        }
    }

    public static void showToast(Context ctx, int duration, int resID) {
        try {
            showToast(ctx, duration, ctx.getString(resID));
        } catch (Exception e) {

        }
    }

    public static void showToastBlack(final Context ctx, final String text) {
        try {
            final Toast toast = Toast.makeText(ctx, text, Toast.LENGTH_SHORT);
            View mNextView = toast.getView();
            if (mNextView != null)
                mNextView.setBackgroundResource(R.mipmap.uploader_dialog_bg);

            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }catch (Exception e){

        }
    }

    /**
     * Toast一个图片
     */
    public static Toast showToastImage(Context ctx, int resID) {
        final Toast toast = Toast.makeText(ctx, "", Toast.LENGTH_SHORT);
            View mNextView = toast.getView();
            if (mNextView != null)
                mNextView.setBackgroundResource(resID);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return toast;
    }

    /**
     * Toast一个图片,拍摄界面使用,在预览画面居中.,需要传一个top
     */
    public static Toast showToastImage(Context ctx, int resID, int top) {
        final Toast toast = Toast.makeText(ctx, "", Toast.LENGTH_SHORT);
        View mNextView = toast.getView();
        if (mNextView != null)
            mNextView.setBackgroundResource(resID);
        toast.setGravity(Gravity.TOP, 0, top);
        toast.show();
        return toast;
    }


    public static void showToast(final Context ctx, final int duration, final String text) {
        try {
            cancel();
            if (mToast == null) {
                Log.e("showToast", "mToast == null");
                createToast(duration, text);
            } else {
                Log.e("showToast", "mToast != null");
                mToast.setDuration(duration);
                mToast.setText(text);
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mToast.show();
                }
            });
            Log.e("showToast", "mToast  show");
        } catch (Exception e) {

        }
    }

    private static void createToast(int duration, String text) {
        synchronized (ToastUtils.class) {
            if (mToast == null) {
                Log.e("showToast", "createToast");
                mToast = Toast.makeText(BaseApp.get().getContext(), text, duration);
                mToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, utils.getScreenHeight(BaseApp.get().getContext()) / 4);
            }
        }
    }

    private static Toast mToast;

    //public static void showToastErrorTip(int resid) {
    //    Context context = BaseApp.get();
    //    if (context != null) {
    //        Toast toast = new Toast(context);
    //        View view = RelativeLayout.inflate(context, R.layout.mpuilibs_toast_layout, null);
    //        TextView mNextView = (TextView) view.findViewById(R.id.toast_name);
    //        toast.setView(view);
    //        mNextView.setText(resid);
    //        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, DeviceUtils.getScreenHeight(context) / 4);
    //        toast.show();
    //    }
    //}

    /**
     * 在UI线程运行弹出
     */
    public static void showToastOnUiThread(final Activity ctx, final String text) {
        if (ctx != null) {
            ctx.runOnUiThread(new Runnable() {
                public void run() {
                    showToast(ctx, text);
                }
            });
        }
    }

    private static Handler handler = new Handler(Looper.getMainLooper());

    private static Object synObj = new Object();

    /**
     * new method
     *
     * @param act
     * @param msg
     */
    public static void showMessage(final Context act, final String msg) {
        try {
            showMessage(act, msg, Toast.LENGTH_SHORT);
        } catch (Exception e) {

        }

    }

    /**
     * new method
     *
     * @param act
     * @param msg
     */
    public static void showMessage(final Context act, final int msg) {
        try {
            showMessage(act, msg, Toast.LENGTH_SHORT);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    private static Toast showToast;

    public static void showMessage(final Context context, final String msg, final int len) {
        executorService.submit(new Runnable() {
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (synObj) {
                            cancel();
                            if (context != null) {
                                showToast = Toast.makeText(context, msg, len);
                                try {
                                    showToast.show();
                                } catch (Exception e) {
                                }
                            }
                        }
                    }
                });
            }
        });
    }

    public static void showMessage(final Context context, final int msg, final int len) {
        executorService.submit(new Runnable() {
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (synObj) {
                            cancel();
                            if (context == null) {
                                if (BaseApp.get() != null) {
                                    showToast = Toast.makeText(BaseApp.get(), msg, len);
                                }
                            } else {
                                showToast = Toast.makeText(context, msg, len);
                            }

                            showToast.show();
                        }
                    }
                });
            }
        });
    }

    /**
     * toast取消
     */
    public static void cancel() {

        if (mToast != null) {
            mToast.cancel();
        }

    }

}
