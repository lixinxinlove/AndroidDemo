package com.lee.androiddemo.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lee.androiddemo.ILeeAidlInterface;
import com.lee.androiddemo.R;
import com.lee.androiddemo.service.LeeService;

public class ServiceActivity extends AppCompatActivity {

    private ILeeAidlInterface ieeAidlInterface;


    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        textView = (TextView) findViewById(R.id.tv);

        Intent intent = new Intent(this, LeeService.class);
        bindService(intent, conn, Service.BIND_AUTO_CREATE);
    }


    private ServiceConnection conn = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ieeAidlInterface = ILeeAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            ieeAidlInterface = null;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (conn != null) {
            unbindService(conn);
        }
    }


    public void add(View view) {
        int content;
        try {
            content = ieeAidlInterface.add(10, 20);
            textView.setText(content + "");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
