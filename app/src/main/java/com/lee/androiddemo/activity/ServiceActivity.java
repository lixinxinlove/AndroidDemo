package com.lee.androiddemo.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lee.androiddemo.GlideApp;
import com.lee.androiddemo.ILeeAidlInterface;
import com.lee.androiddemo.R;
import com.lee.androiddemo.service.LeeService;

import java.io.File;

public class ServiceActivity extends AppCompatActivity {


    String url = "https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg";

    private ILeeAidlInterface ieeAidlInterface;

    private TextView textView;

    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        textView = (TextView) findViewById(R.id.tv);

        imageView = (ImageView) findViewById(R.id.iv_net);

        Intent intent = new Intent(this, LeeService.class);
        bindService(intent, conn, Service.BIND_AUTO_CREATE);

        GlideApp.with(this)
                .load(url)
                .centerCrop()
                .dontAnimate()
                .error(R.mipmap.mao)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);

        //    .transform(new CircleCrop())   // 1.CircleCrop() 圆     2.RoundedCorners(20) 圆角
    }

    private void glideConfig() {
        String filePath = Environment.getExternalStorageDirectory().getPath() + "/Glide";
        File file1 = GlideApp.getPhotoCacheDir(this, filePath);
        if (file1.exists()) {
            deleteFolderFile(filePath, true);
        }
    }

    /**
     * 删除指定目录下的文件，这里用于缓存的删除
     *
     * @param filePath       filePath
     * @param deleteThisPath deleteThisPath
     */
    private void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {
                    File files[] = file.listFiles();
                    for (File file1 : files) {
                        deleteFolderFile(file1.getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {
                        file.delete();
                    } else {
                        if (file.listFiles().length == 0) {
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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


    public void deleteFile(View view) {
        glideConfig();
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
