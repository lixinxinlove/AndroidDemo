package com.lee.androiddemo.activity;

import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.lee.androiddemo.R;
import com.lee.androiddemo.dialog.LeeDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DialogFragmentActivity extends BaseActivity {


    @BindView(R.id.btn_menu)
    Button btnMenu;

    @BindView(R.id.list_view_menu)
    ListView listView;


    List<String> mData;


    PopupMenu popupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mData = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            mData.add("lee" + i);
        }


        popupMenu = new PopupMenu(this, btnMenu);
        popupMenu.inflate(R.menu.menu2);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.e("lee", item.getTitle().toString());
                return false;
            }
        });


        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mData.size();
            }

            @Override
            public String getItem(int position) {
                return mData.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                convertView = View.inflate(DialogFragmentActivity.this, R.layout.item_view, null);

                TextView textView = (TextView) convertView.findViewById(R.id.tv_top);
                textView.setText(mData.get(position));
                return convertView;
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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupMenu = new PopupMenu(DialogFragmentActivity.this, view);
                popupMenu.inflate(R.menu.menu2);
                popupMenu.setGravity(Gravity.RIGHT);
                popupMenu.show();
            }
        });

    }

    @Override
    protected void initData() {

    }


    private void showDialog() {
        LeeDialog ldf = new LeeDialog();
        ldf.show(getSupportFragmentManager(), "df");
    }


}
