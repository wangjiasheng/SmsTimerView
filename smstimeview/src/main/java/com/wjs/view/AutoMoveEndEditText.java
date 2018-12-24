package com.wjs.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

public class AutoMoveEndEditText extends EditText {
    public AutoMoveEndEditText(Context context) {
        super(context);
    }
    public AutoMoveEndEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public AutoMoveEndEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AutoMoveEndEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setTextAndMoveToEnd(String textInfo) {
        if(textInfo!=null) {
            setText(textInfo);
            setSelection(textInfo.length());
        }
    }
}
