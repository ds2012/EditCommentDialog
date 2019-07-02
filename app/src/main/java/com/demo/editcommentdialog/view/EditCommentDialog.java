package com.demo.editcommentdialog.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.editcommentdialog.R;
import com.demo.editcommentdialog.utils.NetworkUtils;
import com.demo.editcommentdialog.utils.StringUtils;
import com.demo.editcommentdialog.utils.ToastUtils;

public class EditCommentDialog extends Dialog {


    public interface EditCommentCall {

        void onDismiss();

        void send(String commentStr, String[] extraParams);

        void onShow();
    }

    private Context context;
    private TextView tv_send;
    private CatchKeyBackEditText edit_comment;
    private EditCommentCall editCommentCall;
    private String[] extraParams;
    private ImageView iv_send_comment_user;
    private int maxWord = 100;

    private int currentNameCount;

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (after > 0) {
                currentNameCount = start;
            }

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > maxWord) {
                if (edit_comment != null) {
                    s.delete(maxWord, s.length());
                }
            }
            if (currentNameCount == 0) {
                currentNameCount = s.length();
            }
            if (s.length() > 0) {
                tv_send.setTextColor(getContext().getResources().getColor(R.color.white));
                tv_send.setBackground(getContext().getResources().getDrawable(R.drawable.stroke_ffc200_radius_14));
            } else {
                tv_send.setTextColor(getContext().getResources().getColor(R.color.color_bdbdbd));
                tv_send.setBackground(getContext().getResources().getDrawable(R.drawable.stroke_f0f0f0_radius_14));
            }
        }
    };

    private Runnable hideSoftInputRunnable = new Runnable() {
        @Override
        public void run() {
            if (context != null) {
                InputMethodManager im =
                        (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                EditText editText = getEditText();
                im.hideSoftInputFromWindow(editText.getWindowToken(),
                        0);
            }
        }
    };

    private Runnable showSoftInputRunnable = new Runnable() {
        @Override
        public void run() {
            if (context != null) {
                InputMethodManager inputManager = (InputMethodManager) context
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                EditText editText = getEditText();
                inputManager.showSoftInput(editText, 0);
            }
        }
    };

    public EditCommentDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public EditCommentDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected EditCommentDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    public void initLayoutNormal(){
        View contentView = LayoutInflater.from(context).inflate(R.layout.feed_dialog_edit_comment, null);
        init(contentView);
    }

    public void initBlackNormal(){
        View contentView = LayoutInflater.from(context).inflate(R.layout.feed_dialog_edit_comment, null);
//        View contentView = LayoutInflater.from(context).inflate(R.layout.feed_black_dialog_edit_comment, null);
        init(contentView);
    }

    private void init(View contentView) {

        //设置对话框布局
        setContentView(contentView);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = context.getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        WindowManager.LayoutParams dialogParams = getWindow().getAttributes();
        dialogParams.dimAmount = 0.6f;
        getWindow().setAttributes(dialogParams);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        tv_send = (TextView) contentView.findViewById(R.id.tv_send);
        iv_send_comment_user = (ImageView) contentView.findViewById(R.id.iv_send_comment_user);
        edit_comment = (CatchKeyBackEditText) contentView.findViewById(R.id.edit_comment);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                reSetExtraParams();
                hideKeyBroad();
                dismiss();
            }
        });
        edit_comment.addTextChangedListener(textWatcher);
        edit_comment.setOnCancelDialogImp(new CatchKeyBackEditText.OnCancelDialogImp() {
            @Override
            public void onCancelDialog() {
                dismiss();
            }
        });
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                float y = edit_comment.getHeight();
                Log.e("onGlobalLayout", "y==" + y);
            }
        });
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NetworkUtils.isNetworkAvailable(getContext())) {
                    ToastUtils.showLongToast(context.getString(R.string.no_net_message));
                    return;
                }
                if (edit_comment != null) {
                    String commentedStr = edit_comment.getText().toString().trim();
                    int mLen = StringUtils.getStringLength(commentedStr);
                    if (mLen == 0) {
                        ToastUtils.showLongToast(context.getString(R.string.mpcommon_title));
                        return;
                    } else if (mLen > 400) {
                        ToastUtils.showLongToast(context.getString(R.string.mpcommon_message_too_long));
                        return;
                    } else {
                        edit_comment.setText("");
                        if (editCommentCall != null) {
                            editCommentCall.send(commentedStr, extraParams);
                            editCommentCall.onDismiss();
                        }
                    }
                }
            }
        });

    }

    public EditCommentCall getEditCommentCall() {
        return editCommentCall;
    }

    public void setEditCommentCall(EditCommentCall editCommentCall) {
        this.editCommentCall = editCommentCall;
    }

    public void pop() {
        if (!isShowing()) {
            if (edit_comment != null) {
                edit_comment.requestFocus();
                edit_comment.setFocusable(true);
                edit_comment.setFocusableInTouchMode(true);
            }
            show();
            if (editCommentCall != null) {
                editCommentCall.onShow();
            }
            showKeyBroad();
        }
    }


    public void pop(String[] extraParams) {
        this.extraParams = extraParams;
        pop();
    }

    private void reSetExtraParams(){
        setEditHint(context.getResources().getString(R.string.common_hide));
        edit_comment.setText("");
        extraParams = null;
    }

    public void dismiss() {
        if (isShowing()) {
            edit_comment.clearFocus();
            super.dismiss();
            if (editCommentCall != null) {
                editCommentCall.onDismiss();
            }
            hideKeyBroad();
        }
    }

    public EditText getEditText() {
        return edit_comment;
    }

    public void setOnCancelDialogImp(CatchKeyBackEditText.OnCancelDialogImp onCancelDialogImp) {
        edit_comment.setOnCancelDialogImp(onCancelDialogImp);
    }

    public void setEditHint(String hintText) {
        if (edit_comment != null) {
            edit_comment.setHint(hintText);
        }
    }


    /**
     * 自动显示键盘
     */
    public void showKeyBroad() {
        getEditText().postDelayed(showSoftInputRunnable, 150);
    }


    /**
     * 自动隐藏键盘
     */
    public void hideKeyBroad() {
        getEditText().postDelayed(hideSoftInputRunnable, 150);
    }


    public void sendClike(){
        if (tv_send != null) tv_send.performClick();
    }
}
