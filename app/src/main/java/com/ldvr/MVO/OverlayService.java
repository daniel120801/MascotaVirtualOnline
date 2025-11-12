package com.ldvr.MVO;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class OverlayService extends Service {
    private WindowManager windowManager;
    private ImageView imageView;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        // Crear notificación para Foreground Service
        Notification notification = new NotificationCompat.Builder(this, "channel_id")
                .setContentTitle("Mi App")
                .setContentText("Mostrando imagen")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build();

        // Iniciar como Foreground Service
        startForeground(1, notification);

        // Tu código de la ventana
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_launcher_foreground);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT
        );

        windowManager.addView(imageView, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (imageView != null) windowManager.removeView(imageView);
        stopForeground(true);
    }
}
