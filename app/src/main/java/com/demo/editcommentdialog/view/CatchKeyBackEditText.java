package com.demo.editcommentdialog.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;


public class CatchKeyBackEditText extends EditText {


    /**
     * 在这里定义一个接口 用于在输入框弹出的时候(评论)的时候  点击back按键不响应onKeyDown  和 onKeyPressed 方法
     * 但是查询api  是可以在自定义的view里面走dispatchKeyEventPreIme这个方法的(这个方法的响应在软键盘的响应之前)
     * 所以当按下back的时候,肯定会优先走这个方法  所以在这里写一个回调接口,那么就可以在调用这个回调的时候出发我们需要
     * 响应的逻辑   (比如dialog隐藏)
     */
    public interface OnCancelDialogImp {
        void onCancelDialog();
    }


    private OnCancelDialogImp mOnCancelDialogImp;

    public CatchKeyBackEditText(Context context) {
        super(context);
    }

    public CatchKeyBackEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CatchKeyBackEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if (mOnCancelDialogImp != null) {
            mOnCancelDialogImp.onCancelDialog();
        }
        return super.dispatchKeyEventPreIme(event);
    }


    public void setOnCancelDialogImp(OnCancelDialogImp onCancelDialogImp) {
        mOnCancelDialogImp = onCancelDialogImp;
    }
}
