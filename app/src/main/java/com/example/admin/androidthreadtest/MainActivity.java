package com.example.admin.androidthreadtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {



    private Button changeBt;
    private TextView tv;
    public static final int UPDATE_TEXT = 1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    //更新UI
                    tv.setText("changed");
                    break;
                    default:break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeBt =(Button) findViewById(R.id.change_text);
        tv = (TextView) findViewById(R.id.textview);

        changeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){
                    case R.id.change_text:
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Message message  = new Message(); //在子线程中发送消息给子线程
                                message.what = UPDATE_TEXT;
                                handler.sendMessage(message);

                            }
                        }).start();
                            break;
                    default:
                }
            }
        });


    }
}
