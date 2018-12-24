package com.wjs.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wjs.view.smstimeview.R;

public class MainActivity extends AppCompatActivity {
    SMSTimerView view;
    SMSTimerView view2;
    Button cesh;
    AutoMoveEndEditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cesh=findViewById(R.id.cesh);
        cesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.resetAndRun();
            }
        });

        editText=findViewById(R.id.text2);
        editText.setTextAndMoveToEnd("自动选区");

        view=findViewById(R.id.smsView);
        view.setDefaultTime(20);
        view.setDefaultstartText("开始");
        view.setDefaultendText("完毕");
        view.setTag(this,"wocao");

        Log.i("wjs_wjs","show");

        view2=findViewById(R.id.smsView2);
        view2.setDefaultTime(20);
        view2.setDefaultstartText("开始");
        view2.setDefaultendText("完毕");
        view2.setTag(this,"wocao1");
    }

}
