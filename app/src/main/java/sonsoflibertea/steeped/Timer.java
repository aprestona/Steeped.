package sonsoflibertea.steeped;

/**
 * Created by james_000 on 4/4/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class Timer extends Activity { // timer inherits all of activity
    public static long timeSet;
    Button btnStart;
    Button btnStop;
    Button btnPause;
    TextView textViewTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);

        btnStart = (Button)findViewById(R.id.btnStart);
        btnStop = (Button)findViewById(R.id.btnStop);
        btnPause = (Button)findViewById(R.id.btnPause);
        btnStop.setVisibility(View.GONE);
        btnPause.setVisibility(View.GONE);
        textViewTime = (TextView)findViewById(R.id.textViewTime);
        SharedPreferences prefs = this.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
        int time = prefs.getInt("com.example.app.base_time", 0);
        float strength = prefs.getFloat("com.example.app.strength", 0);
        time *=strength;
        timeSet = time;

//        textViewTime.setText(time);

        final CounterClass timer = new CounterClass(timeSet*1000, 1000);
        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timer.start();
                btnStart.setVisibility(View.GONE);
                btnStop.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.VISIBLE);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timer.cancel();
                btnStart.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.GONE);
                btnPause.setVisibility(View.GONE);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timer.cancel();

                btnStart.setVisibility(View.VISIBLE);
            }
        });
    }

    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            timeSet = millis/1000;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);
            textViewTime.setText(hms);
        }

        public void onFinish() {
            textViewTime.setText("Completed.");
        }
    }
}
