package project.listick.fake;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;


import project.listick.fake.UI.MapsActivity;

public class MainServiceControl {

    /**
     *  this is a second part of the service run after the FixedSpooferService
     *  and its responsible for running forground service and initTestProvider in MockLocProvider
     */

    public static final String SERVICE_CONTROL_ACTION = "project.listick.fakegps.actionservice.daemons.ctrl";
    private final Context mContext;

    public MainServiceControl(Context context){
        this.mContext = context;
        IntentFilter filter = new IntentFilter();
        filter.addAction(MainServiceControl.SERVICE_CONTROL_ACTION);
    }

    public static void startServiceForeground(Service context) {
        String NOTIFICATION_CHANNEL_ID = "project.listick.fakegps_SPOOFING_STATUS";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, context.getString(R.string.notification_status_control), NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.setLightColor(Color.BLACK);

            if (manager != null)
                manager.createNotificationChannel(channel);
        }

        Intent openActivity = new Intent(context, MapsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, openActivity, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        builder.setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_pin)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .setContentTitle(context.getText(R.string.app_name))
                .setContentText(context.getText(R.string.notify_status_description));

        int NOTIFICATION_ID = 2;
        context.startForeground(NOTIFICATION_ID, builder.build());
    }


}
