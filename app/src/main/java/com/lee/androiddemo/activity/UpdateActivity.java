package com.lee.androiddemo.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lee.androiddemo.R;
import com.lee.androiddemo._interface.DownloadListener;
import com.lee.androiddemo.http.DownloadTask;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;

public class UpdateActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = "UpdateActivity";

    @BindView(R.id.btn_down_app)
    Button btnDownApp;

    @BindView(R.id.tv_progress)
    TextView tvProgress;


    private DownloadTask downloadTask;

    private File mFile;

    private String downloadUrl = "http://download.evente.cn/evente/android/EventeApp_v_1.0.5.apk";

    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String path = Environment.getExternalStorageDirectory().getPath() + "/lee.apk";
        mFile = new File(path);

        if (!mFile.exists()) {
            try {
                mFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected int getContextView() {
        return R.layout.activity_update;
    }

    @Override
    protected void initListener() {
        btnDownApp.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_down_app:
                onCheckPermission();
                break;
        }
    }

    protected void onCheckPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestWriteExternalPermissions();
        } else {
            downApp();
        }
    }

    private void requestWriteExternalPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 100: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    downApp();
                } else {
                    Toast.makeText(this, "没有Permissions", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    //下载
    private void downApp() {
        downloadTask = new DownloadTask(downloadListener, mFile);
        downloadTask.execute(downloadUrl);
    }

    private DownloadListener downloadListener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            Log.e(TAG, progress + "");

            tvProgress.setText("下载进度" + progress + "%");

        }

        @Override
        public void onSuccess() {
            Log.e(TAG, "成功");
            installApk(UpdateActivity.this, mFile);
        }

        @Override
        public void onFailed() {
            Log.e(TAG, "失败");
        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onCanceled() {

        }
    };


    public static void installApk(Context context, File apkFile) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".fileProvider", apkFile);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        } else {
            uri = Uri.fromFile(apkFile);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        }

        context.startActivity(intent);
    }


}
