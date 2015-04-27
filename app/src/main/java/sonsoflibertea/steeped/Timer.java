package sonsoflibertea.steeped;

/**
 * Created by james_000 on 4/4/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;

public class Timer extends Activity { // timer inherits all of activity
    public static long timeSet;

    Button btnReStart;
    Button btnStop;
    TextView textViewTime;
    TextView tv_temp;
    Vibrator v;
    Drawable replaypic;

    boolean isRunning = false;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);

        btnReStart = (Button)findViewById(R.id.btnReStart);
        btnStop = (Button)findViewById(R.id.btnStop);
        btnStop.setEnabled(false);
        textViewTime = (TextView)findViewById(R.id.textViewTime);
        tv_temp = (TextView)findViewById(R.id.tv_temp);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        replaypic = getResources().getDrawable(R.drawable.ic_replay);




        SharedPreferences prefs = this.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
        int time = prefs.getInt("com.example.app.base_time", 0);
        float strength = prefs.getFloat("com.example.app.strength", 0);
        int tempF = prefs.getInt("com.example.app.tempF", 0);
        int tempC = prefs.getInt("com.example.app.tempC",0);
        time *=strength;
        timeSet = time;
        tv_temp.setText(tempC + " \u00B0C / " + tempF + " \u00B0F");




        // making a timer object!
        final CounterClass timer = new CounterClass(timeSet*1000, 1000);


        btnReStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(!isRunning) // Timer not yet running  so start it
                {
                    timer.start();
                    btnReStart.setEnabled(false);
                    btnStop.setEnabled(true);
                }

                else if(isRunning) // Timer is already running so stop and start again
                {
                    timer.cancel();
                    timer.start();
                    btnReStart.setEnabled(false);
                    btnStop.setEnabled(true);
                }

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                timer.cancel();
                btnReStart.setText("Restart");
                btnReStart.setBackground(replaypic);
                btnReStart.setEnabled(true);
                btnStop.setEnabled(false);
                textViewTime.setText("Steeped.");

            }
        });
    }

    public class CounterClass extends CountDownTimer {


        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);
            textViewTime.setText(hms);
        }

        public void onFinish() {
            textViewTime.setText("Steeped.");
            long[] pattern = {0,200,200,200,200,500};
            btnReStart.setEnabled(true);
            btnStop.setEnabled(false);
            if(v.hasVibrator()) // if there is a hardware vibrator
            {
                v.vibrate(pattern, -1); // vibrate using the above pattern.
            }
            try {
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }
}