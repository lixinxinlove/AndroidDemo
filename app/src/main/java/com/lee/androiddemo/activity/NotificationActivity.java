package com.lee.androiddemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lee.androiddemo.R;

public class NotificationActivity extends AppCompatActivity {


    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        btn = (Button) findViewById(R.id.btn_not);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
            }
        });


    }

    private void init() {
//        NotificationManager mNotificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//// The ID of the channel.
//        String id = "my_channel_01";
//// The user visible name of the channel.
//        CharSequence name = getString(R.string.channel_name);
//// The user visible description of the channel.
//        String description = getString(R.string.channel_description);
//        int importance = NotificationManager.IMPORTANCE_LOW;
//        NotificationChannel mChannel = new NotificationChannel(id, name, importance);
//// Configure the notification channel.
//        mChannel.setDescription(description);
//        mChannel.setShowBadge(false);
//        mNotificationManager.createNotificationChannel(mChannel);
//
//
//        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        int notificationID = 1;
//        String CHANNEL_ID = "my_channel_01";
//// Set a message count to associate with this notification in the long-press menu.
//        int messageCount = 3;
//// Create a notification and set a number to associate with it.
//        Notification notification = new Notification.Builder(NotificationActivity.this)
//                .setContentTitle("New Messages")
//                .setContentText("You've received 3 new messages.")
//                .setSmallIcon(R.mipmap.icon_logo)
//                .setChannelId(CHANNEL_ID)
//                .setNumber(messageCount)
//                .build();
//// Issue the notification.
//        mNotificationManager.notify(notificationID, notification);


    }
}
