package com.lee.androiddemo.activity;


import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.lee.androiddemo.R;


public abstract class BasePermissionActivity extends BaseActivity {

    private static final int REQUEST_PERMISSION_CODE = 100;

    public abstract String[] getRequestPermissions();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            onCheckPermission();
        }
    }

    protected void onCheckPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            requestWriteExternalPermissions();
        } else {
            onPermissionsResultGranted();
        }
    }


    private AlertDialog alertDialog;

    private void requestWriteExternalPermissions() {
        String grantedMessage = getResources().getString(R.string.granted_base_permission_for_app);
        if (alertDialog != null && !alertDialog.isShowing()) {
            alertDialog.show();
        } else {
            alertDialog = new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setMessage(grantedMessage)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissions();
                        }
                    }).create();
            alertDialog.show();
        }

    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, getRequestPermissions(), REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onPermissionsResultGranted();
                } else {
                    onPermissionsResultDenied();
                }
                return;
            }
        }
    }

    public abstract void onPermissionsResultGranted();

    public abstract void onPermissionsResultDenied();

}
