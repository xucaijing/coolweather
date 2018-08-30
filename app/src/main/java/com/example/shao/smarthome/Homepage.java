package com.example.shao.smarthome;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.time.Clock;

public class Homepage extends AppCompatActivity {
   static   int flag=0;
    ClientHelper clientHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ActionBar actionBar=getSupportActionBar();//隐藏标题栏
        actionBar.hide();

        //取出之前保存的数据
        if (savedInstanceState!=null){
            String tempData=savedInstanceState.getString("data_key");
        }

        LinearLayout linearLayout= findViewById(R.id.clock);//定时的点击事件
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Homepage.this, com.example.shao.smarthome.Clock.class);
                startActivity(intent);
            }
        });
        LinearLayout linearLayout1=findViewById(R.id.personal);//个人这个layout的点击事件
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Homepage.this,Personal.class);
                startActivity(intent);
            }
        });
        //loginSwitch=findViewById(R.id.switch_1);
        final ImageView imageView2=(ImageView)findViewById(R.id.switch_1);//开关图片的点击事件
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//图片的切换
flag=Integer.valueOf(clientHelper.read());
                if (flag==0){
                    imageView2.setImageResource(R.drawable.on_img);
                    flag=1;
                    //TODO
                } else if(flag==1){
                    imageView2.setImageResource(R.drawable.off_img);
                    flag=0;
                    //TODO
                }

                clientHelper.send((String.valueOf(flag)));
            }
        });
    }

    //如果点击了别的活动，回调活动的时候可以保留之前的数据
    @Override
    protected  void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        String tempData="Something you just typed";
        outState.putString("data_key",tempData);
    }
}
