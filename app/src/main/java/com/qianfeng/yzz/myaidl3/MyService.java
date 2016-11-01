package com.qianfeng.yzz.myaidl3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {


    private Manger manger;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        manger = new Manger();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return manger.asBinder();
    }

    class Manger extends MyIDLManger.Stub{
        private List<Student>  studentList = new ArrayList<>();

        @Override
        public List<Student> getList() throws RemoteException {
            return studentList;
        }

        @Override
        public void add(Student student) throws RemoteException {
            studentList.add(student);
        }
    }
}
