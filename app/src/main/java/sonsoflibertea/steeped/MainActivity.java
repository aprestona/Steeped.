package sonsoflibertea.steeped;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class unusedMainActivity extends Activity
{
    //Initializes TextViews to display total and tip
    private TextView timerTextView;
    private Button goButton;
    private Button shortTimeButton;
    private Button longTimeButton;
//    private EditText et;

    @Override
    //When this activity is called, onCreate is called
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //How this activity actually looks is set inside activity_main.xml
        setContentView(R.layout.activity_main);
//        et = (EditText) findViewById(R.id.time_in);

		/*Set the texts views so they display according to the parameters in result.xml*/
        timerTextView = (TextView) findViewById(R.id.timer);
        //Runs the method to use the values calculated in the Main Activity class

        //Initializes button to the parameters in result.xml
        goButton = (Button) findViewById(R.id.go_button);
        shortTimeButton = (Button) findViewById(R.id.time_short_bttn);
        longTimeButton = (Button) findViewById(R.id.time_long_bttn);


        goButton.setOnClickListener(new OnClickListener() {
            @Override
            //If clicked, call the finish method
            public void onClick(View v) {
//                String text = et.getText().toString();

                long countdown = 100; // gets value in seconds
                countdown = 1000*countdown; // converts to milliseconds
                Intent intent = new Intent(unusedMainActivity.this, TeaType.class);
                startActivity(intent);
                initializeTextViews(countdown);

            }
        });

    }

    private void initializeTextViews(long countdown)
    {
        //Sets the texts to display the values

        new CountDownTimer(countdown, 1000){
            public void onTick(long millUntilFinish){
                if(millUntilFinish>60000)
                timerTextView.setText("Time Remaining: "+ Math.round(millUntilFinish/60000) +
                        ":"+ (millUntilFinish%60000)/1000);
                else
                    timerTextView.setText("Time Remaining: "+(millUntilFinish/1000));
            }
            public void onFinish(){
                timerTextView.setText("Tea is done!");
            }
        }.start();
    }
}
