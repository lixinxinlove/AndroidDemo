package com.lee.androiddemo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.lee.androiddemo.R;
import com.lee.androiddemo.dialog.LeeDialog;

import butterknife.BindView;

public class DialogFragmentActivity extends BaseActivity {


    @BindView(R.id.btn_menu)
    Button btnMenu;

    PopupMenu popupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        popupMenu = new PopupMenu(this, btnMenu);
        popupMenu.inflate(R.menu.menu2);


        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.e("lee", item.getTitle().toString());
                return false;
            }
        });


    }

    @Override
    protected int getContextView() {
        return R.layout.activity_dialog_fragment;
    }

    @Override
    protected void initListener() {

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });




    }

    @Override
    protected void initData() {

    }


    private void showDialog() {
        LeeDialog ldf = new LeeDialog();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ldf.show(ft, "df");
    }


}
