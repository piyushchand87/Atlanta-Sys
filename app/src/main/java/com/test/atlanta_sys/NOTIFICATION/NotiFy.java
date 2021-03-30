package com.test.atlanta_sys.NOTIFICATION;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.bumptech.glide.load.engine.Resource;
import com.test.atlanta_sys.R;
import com.test.atlanta_sys.RETROFIT.MainActivity;
import com.test.atlanta_sys.Selection_Class;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by mktuser on 28/March/2021
 */
public class NotiFy extends AppCompatActivity {
    Button Button_Notify;
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        Button_Notify = (Button) findViewById(R.id.Button_Notify);
        Button_Notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(NotiFy.this, "My Notification");
                    builder.setContentTitle("Local Notification");

                    builder.setSmallIcon(R.drawable.notifications_white_24dp);
                    builder.setContentTitle("Local Notification Tray");
//                    builder.setContentText("Hello Atlanties, How are You?");
                    builder.setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),
                            R.mipmap.atlanta));
                    Context context = getApplicationContext();
                    builder.setColor(ContextCompat.getColor(context, R.color.teal_200));
                    builder.setStyle(new NotificationCompat.BigTextStyle().bigText("LOcal"));
                    builder.setStyle(new NotificationCompat.BigTextStyle().bigText("This Notification is only for Testing Purpose").setSummaryText("#Hello Atlanta"));
                    builder.setShowWhen(true);
                    builder.setAutoCancel(true);

                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(NotiFy.this);
                    managerCompat.notify(1, builder.build());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    public void CustomNotification() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.customnotification_noti);
        String strtitle = "Atlanta";
        String strtext = "Hello Atlanties, How are you?";
        Intent intent = new Intent(this, NotiFy.class);
        intent.putExtra("title", strtitle);
        intent.putExtra("text", strtext);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.atlanta)
                .setTicker("Sample")
                .setAutoCancel(true) .setContentIntent(pIntent)
                .setContent(remoteViews);
        remoteViews.setImageViewResource(R.id.imagenotileft,R.drawable.notifications_white_24dp);
        remoteViews.setImageViewResource(R.id.imagenotiright,R.mipmap.atlanta);
        remoteViews.setTextViewText(R.id.title,"Title");
        remoteViews.setTextViewText(R.id.text,"Text");
        // Create Notification Manager
        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Build Notification with Notification Manager
        notificationmanager.notify(0, builder.build());
    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(this, Selection_Class.class);
        startActivity(in);
        finish();
    }
}
