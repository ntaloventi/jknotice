package com.jalurkhusus.jknotice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.jalurkhusus.jkNotice.R;

public class JkNotice {

    private Context context;
    private Intent resIntent;
    private int noticeId;
    private String title = "Title";
    private String body = "Body";
    private String header = "";
    private Bitmap bitmap;

    public JkNotice(Context context, Intent resIntent, int id) {
        this.context = context;
        this.resIntent = resIntent;
        this.noticeId = id;
    }

    public void setContent(String title, String body, String header) {
        this.title = title;
        this.body = body;
        this.header = header;
    }

    public void addBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void showNotice(){
        String channelId = context.getResources().getString(R.string.notice_channel_id);
        String channelName = context.getResources().getString(R.string.notice_channel_name);
        String channelDesc = context.getResources().getString(R.string.notice_channel_desc);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setChannelId(channelId)
            .setOngoing(false);

        if (bitmap != null){
            notificationBuilder.setLargeIcon(bitmap);
            notificationBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            AudioAttributes attributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build();

            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName,
                    NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription(channelDesc);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setSound(defaultSoundUri, attributes);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Bundle bundle = new Bundle();
        bundle.putBoolean("from_fcm", true);
        bundle.putString("title", title);
        bundle.putString("body", body);
        resIntent.putExtras(bundle);
        resIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(resultPendingIntent);
        notificationBuilder.build().flags |= Notification.FLAG_AUTO_CANCEL;
        Notification notification = notificationBuilder.build();
        notificationManager.notify(noticeId, notification);
    }

}
