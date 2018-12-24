package com.wjs.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;


public class SMSTimerView extends TextView {
    private long max_interval = -1;
    private long current_interval=-1;
    private String defaultText="";
    private String startText="";
    private String tag="q002";
    private OnStatusChangeListener onStatusChangeListener;

    public SMSTimerView(Context context) {
        super(context);
    }

    public SMSTimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SMSTimerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SMSTimerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void saveCurrentTime(){
        SharedPreferences sharedPreferences=getContext().getSharedPreferences(SMSTimerView.class.getName(),MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putLong(tag,System.currentTimeMillis());
        editor.commit();
    }
    public long readLessTime(){
        SharedPreferences sharedPreferences=getContext().getSharedPreferences(getClass().getName(),MODE_PRIVATE);
        long lastTime=sharedPreferences.getLong(tag,max_interval);
        long jiange=(System.currentTimeMillis()-lastTime)/1000;  //两次时间间隔的秒数
        return jiange;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init(getContext());
    }

    public void init(Context context) {
        Log.i("wjs_wjs","init");
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAndRun();
            }
        });
        long jiange=readLessTime();
        if(max_interval>jiange){
            current_interval=max_interval-jiange;
            post(runnable);
        }else{
            setText(startText);
        }
    }
    public void resetAndRun(){
        if(isEnabled()) {
            if(onStatusChangeListener!=null){
                onStatusChangeListener.start();
            }
            saveCurrentTime();
            setEnabled(false);
            current_interval = max_interval;
            post(runnable);
        }
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(current_interval<=0){
                setEnabled(true);
                setText(defaultText);
                removeCallbacks(runnable);
                if(onStatusChangeListener!=null){
                    onStatusChangeListener.complie();
                }
            }else{
                if(onStatusChangeListener!=null){
                    onStatusChangeListener.timecChange(current_interval);
                }
                setText(String.valueOf(current_interval--));
                postDelayed(runnable,1000);
            }
        }
    };

    public void setDefaultTime(int max_interval) {
        this.max_interval = max_interval;
        this.current_interval = max_interval;
    }

    public void setDefaultstartText(String defaultText) {
        setText(defaultText);
        this.startText=defaultText;
    }
    public void setDefaultendText(String defaultendText){
        this.defaultText = defaultendText;
    }
    public void setOnStatusChangeListener(OnStatusChangeListener onStatusChangeListener) {
        this.onStatusChangeListener = onStatusChangeListener;
    }

    public void setTag(Object ob,Object tag){
        this.tag=ob.getClass().getName()+"_" + tag;
    }
    public interface OnStatusChangeListener{
        public void start();
        public void complie();
        public void timecChange(long time);
    }
}
