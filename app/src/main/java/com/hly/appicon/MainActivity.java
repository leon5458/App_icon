package com.hly.appicon;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private PackageManager mPackageManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPackageManager=getApplicationContext().getPackageManager();
        updateIcon();
    }
    //检查图标并更换
    public void updateIcon(){
        //获取当前的ComponentName
        ComponentName currentName=getComponentName();
        if (isDouble11()&&!currentName.getClassName().equals("com.hly.appicon.tag_11")){
            ComponentName double11=new ComponentName(getBaseContext(),"com.hly.appicon.tag_11");
            disableComponent(currentName);
            enableComponent(double11);
            Log.e("2018","双11");
        }else if(isDouble12()&&!currentName.getClassName().equals("com.hly.appicon.tag_12")){
            ComponentName double12=new ComponentName(getBaseContext(),"com.hly.appicon.tag_12");
            disableComponent(currentName);
            enableComponent(double12);
            Log.e("2018","双12");
        }
    }

    //是否双11期间
    public boolean isDouble11(){
        Calendar calendar=Calendar.getInstance();
        calendar.set(2018,11-1,1);
//        calendar.set(2018,9-1,1);
        long startTime=calendar.getTime().getTime();
        calendar.set(2018,11-1,11);
        long endTime=calendar.getTime().getTime();
        long nowTime=System.currentTimeMillis();
        if(startTime<nowTime&&nowTime<endTime){
            return true;
        }
        return false;
    }

    //是否双12期间
    public boolean isDouble12(){
        Calendar calendar=Calendar.getInstance();
        calendar.set(2018,9-1,1);
        long startTime=calendar.getTime().getTime();
        calendar.set(2018,12-1,12);
        long endTime=calendar.getTime().getTime();
        long nowTime=System.currentTimeMillis();
        if(startTime<nowTime&&nowTime<endTime){
            return true;
        }
        return false;
    }

    //启用组件
    public void enableComponent(ComponentName componentName){
        mPackageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP);
    }

    //隐藏组件
    public void disableComponent(ComponentName componentName){
        mPackageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
    }

}
