package code.three.service_test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by qiqicode on 13-8-8.
 */
public class TestService extends Service {
    private boolean stop = false;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        while(!stop)
//        {
//            try {
//                Thread.sleep(2000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
        new Thread()
        {
            @Override
            public void run() {
                while (!stop)
                {
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ServiceTest.TEMP ++;
                }
            }
        }.start();

//        }

        return START_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        this.stop = true;
        ServiceTest.TEMP = 0;
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.stop = true;
        ServiceTest.TEMP = 0;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
