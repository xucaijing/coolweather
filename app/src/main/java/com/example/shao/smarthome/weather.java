package com.example.shao.smarthome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by 徐蔡靖 on 2018/8/28.
 */

public class weather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ActionBar actionBar=getSupportActionBar();//隐藏标题栏
        actionBar.hide();
        Button button=(Button) findViewById(R.id.loginbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(weather.this,Homepage.class);
                startActivity(intent);
            }
        });

    }
}
