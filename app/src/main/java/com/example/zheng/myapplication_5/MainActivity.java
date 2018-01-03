package com.example.zheng.myapplication_5;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SeekBar bar = (SeekBar)findViewById(R.id.seekBar);
        final TextView textView = (TextView) findViewById(R.id.textview);
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                textView.setText(msg.arg1+"");
                bar.setMax(100);
                bar.setProgress(msg.arg1);
        }
        };
        final Runnable myWorker = new Runnable() {
            @Override
            public void run() {
            int progess = 0;
                while (progess<=100){
                    Message msg = new Message();
                    msg.arg1 = progess;
                    handler.sendMessage(msg);
                    progess+=1;
                    try{
                        Thread.sleep(100);
                    }catch(InterruptedException e){
                    e.printStackTrace();
                    }
                }
                Message msg = handler.obtainMessage();
                msg.arg1 = -1;
                handler.sendMessage(msg);
            }

        };
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread = new Thread(null, myWorker, "WorkThread");

            workThread.start();
        }
        });
    }
}
