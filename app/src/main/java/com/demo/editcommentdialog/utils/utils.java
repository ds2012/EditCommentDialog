package com.demo.editcommentdialog.utils;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class utils {
    public static int getScreenHeight(Context context) {
        if (context == null) {
            return 0;
        }
        Display display =
                ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        return display.getHeight();
    }
}
