package com.example.shao.smarthome;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import static java.util.Calendar.HOUR_OF_DAY;

public class Clock extends AppCompatActivity implements View.OnClickListener{


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tvSelectTime:
                showSelectDailog();
                break;

                default:
                    break;
        }
    }
    private void timeTrigger(int hour,int minute)
    {
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,0);
        Date date=calendar.getTime();
        if(date.before(new Date()))
        {
            date=this.addDay(date,1);
        }
        Log.i("wxy","date:"+date);
        timer=new Timer(true);
        timer.schedule(new Task(),date,1000);
    }


    public Date addDay(Date date, int num) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,num);
        return calendar.getTime();
    }

    private void showSelectDailog() {
        TimePickerDialog.OnTimeSetListener listener=new TimePickerDialog.OnTimeSetListener()
        {

            @Override
            public void onTimeSet(TimePicker arg0, int hour, int minute) {
                String time="";
                if (hour<10)
                {
                    String h="0"+hour;
                    //<10 +0
                    time+=h;
                }
                else
                    time+=hour;
                time+=":";
                if(minute<10)
                {
                    String m="0"+minute;
                    time+=m;
                }
                else{
                    time+=minute;
                }
                clientHelper.read();

                if (Homepage.flag==0)
                {
                    Homepage.flag=1;
                    ////////////定时触发事件
                }
                else {
                    Homepage.flag=0;
                }
                clientHelper.send((String.valueOf(Homepage.flag)));
                timeTrigger(hour,minute);
            }
        };
        TimePickerDialog dialog=new TimePickerDialog(this,listener,0,0,true
        );
        dialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        llSelectTime=(LinearLayout)findViewById(R.id.llSelectTime);
        tvSelectTime=(TextView)findViewById(R.id.tvSelectTime);
        tvSelectTime.setOnClickListener(this);
        swOnOfOff=(Switch)findViewById(R.id.swOnOfOff);

        swOnOfOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    llSelectTime.setVisibility(View.VISIBLE);
                }
                else {
                    llSelectTime.setVisibility(View.GONE);
                    timer.cancel();
                }
            }
        });

        ActionBar actionBar=getSupportActionBar();//隐藏标题栏
        actionBar.hide();
        LinearLayout linearLayout=findViewById(R.id.personal);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Clock.this,Personal.class);
                startActivity(intent);
            }
        });
        LinearLayout linearLayout1= findViewById(R.id.homepage);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Clock.this,Homepage.class);
                startActivity(intent);
            }
        });

    }
    private LinearLayout llSelectTime;
    private Switch swOnOfOff;
    private TextView tvSelectTime;
    private Timer timer;
    ClientHelper clientHelper;


}
