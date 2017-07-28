package com.lee.androiddemo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.lee.androiddemo.R;
import com.lee.androiddemo.utils.FileUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

public class PhotoActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_IMAGE_CAPTURE = 6;
    private static final int REQUEST_PHOTO_LIBRARY = 7;


    @BindView(R.id.root_view)
    RelativeLayout rootView;

    @BindView(R.id.btn_select_image)
    Button btnSelectImage;

    private View marketingPopView;

    private RelativeLayout album, camera, btnCancel;

    private PopupWindow btnAddImgPop;

    private File mImageFile;

    private Uri mImageUri;


    private Bitmap mBitmap;

    @BindView(R.id.iv_phone)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContextView() {
        return R.layout.activity_photo;
    }

    @Override
    protected void initListener() {
        btnSelectImage.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        initPopView();
        setPopupWindowStyle();
    }


    private void initPopView() {
        if (marketingPopView != null) {
            return;
        }
        LayoutInflater inflater = LayoutInflater.from(this);
        // 引入窗口配置文件 - 即弹窗的界面
        marketingPopView = inflater.inflate(R.layout.chimelong_myhead_select, null);
        album = (RelativeLayout) marketingPopView.findViewById(R.id.album_layout);
        camera = (RelativeLayout) marketingPopView.findViewById(R.id.camera_layout);
        btnCancel = (RelativeLayout) marketingPopView.findViewById(R.id.cancel_layout);
        setPopupWindowStyle();
    }

    private void setPopupWindowStyle() {
        album.setOnClickListener(this);
        camera.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        marketingPopView.setFocusableInTouchMode(true);
        // PopupWindow实例化
        btnAddImgPop = new PopupWindow(marketingPopView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, true);
        btnAddImgPop.setAnimationStyle(R.style.LeeMenuAnimationFade);
        ColorDrawable dw = new ColorDrawable(0x99000000);
        btnAddImgPop.setBackgroundDrawable(dw);
        btnAddImgPop.setOnDismissListener(onDismissListener);
    }

    private PopupWindow.OnDismissListener onDismissListener = new PopupWindow.OnDismissListener() {

        @Override
        public void onDismiss() {
            rootView.setAlpha(1f);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_PHOTO_LIBRARY) {
            Uri uri = intent.getData();


            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                //imgUpload(path);
                mBitmap = BitmapFactory.decodeFile(path);
                imageView.setImageBitmap(mBitmap);
            }


            String path1 = uri.getPath();
            mBitmap = BitmapFactory.decodeFile(path1);
            imageView.setImageBitmap(mBitmap);


        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            // imgUpload(mImageUri.getPath());

            mBitmap = BitmapFactory.decodeFile(mImageUri.getPath());
            imageView.setImageBitmap(mBitmap);
        }


    }


    /**
     * 打开相册
     */
    private void openPhotoLibraryMenu() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_PHOTO_LIBRARY);
    }

    /**
     * 打开照相机
     */
    private void openImageCaptureMenu() {
        try {
            String filename = _getPhotoFilename(new Date());
            mImageFile = new File(FileUtil.getBasePath(), filename);
            mImageUri = Uri.fromFile(mImageFile);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("SimpleDateFormat")
    private String _getPhotoFilename(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddKms");
        return dateFormat.format(date) + ".jpg";
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_select_image:
                btnAddImgPop.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.album_layout:
                btnAddImgPop.dismiss();
                openPhotoLibraryMenu();
                break;
            case R.id.camera_layout:
                btnAddImgPop.dismiss();
                openImageCaptureMenu();
                break;
            case R.id.cancel_layout:
                btnAddImgPop.dismiss();
                break;
            default:
                break;
        }


    }
}
