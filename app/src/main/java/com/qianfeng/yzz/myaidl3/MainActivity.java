package com.qianfeng.yzz.myaidl3;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection connection;
    private MyService.Manger manger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void add(View view) {
        Student student = new Student();
        student.setAge(19);
        student.setName("yzz");
        try {
            manger.add(student);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void get(View view) {
        try {
            List<Student> studentList = manger.getList();
            if (studentList==null){
                return;
            }
            for (Student student:studentList){
                Log.e("student","===="+student.getName());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
      connection = new ServiceConnection() {
          @Override
          public void onServiceConnected(ComponentName name, IBinder service) {
              manger = (MyService.Manger) MyService.Manger.asInterface(service);
          }

          @Override
          public void onServiceDisconnected(ComponentName name) {

          }
      };
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.qianfeng.yzz.myaidl3","com.qianfeng.yzz.myaidl3.MyService"));
        bindService(intent,connection, Service.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
