package com.example.shao.smarthome;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Personal extends AppCompatActivity {

    private List<Element>elementsList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ActionBar actionBar=getSupportActionBar();//隐藏标题栏
        actionBar.hide();

        initElements();//初始化个人主页的元素数据
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ElementAdapter adapter=new ElementAdapter(elementsList);
        recyclerView.setAdapter(adapter);
        LinearLayout linearLayout=(LinearLayout) findViewById(R.id.homepage);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Personal.this,Homepage.class);
                startActivity(intent);
            }
        });
        LinearLayout linearLayout1=(LinearLayout) findViewById(R.id.clock);
        linearLayout1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(Personal.this,Clock.class);
                startActivity(intent);
            }
        });
    }

    private void initElements(){
        Element personalInfo=new Element("个人信息",R.drawable.personalinfo);
        elementsList.add(personalInfo);
        Element cancel=new Element("账户注销",R.drawable.cancel);
        elementsList.add(cancel);
    }
}
