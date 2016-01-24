package com.example.tasknewvariable;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.tasknewvariable.R;
import com.google.android.gms.gcm.GcmListenerService;

import java.net.URI;

/**
 * Created by deepa on 22/01/2016.
 */
public class GsmListener extends GcmListenerService {
    private static final String TAG = "GcmListener";

    public void onMessageReceived(String from,Bundle data){
        Log.i("hello","Hiiiiii");
        String msg=data.getString("greetMsg");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + data.toString());

        if (from.startsWith("/topics/")) {
            // message received from some topic.
        } else {
            // normal downstream message.
        }

        sendNotification(msg);
    }

    private void sendNotification(String msg) {
        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("msg", msg);

        PendingIntent pendingIntent=PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri soundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder noti_builder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Notification")
                .setContentText(msg)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,noti_builder.build());

    }

}
