package sonsoflibertea.steeped;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by a on 2015/4/6.
 */
public class StrengthDesire extends Activity implements SeekBar.OnSeekBarChangeListener {

    private TextView tv_sd;                                                                         // Declare activity label text
    private SeekBar bar;                                                                            // Declare seekbar object
    private TextView textProgress;                                                                  // Declare seekbar progress text
    private Button continueButton;                                                                  // Declare continue button
    float strengthDesire;
    private Button buttonsd_light;                                                                  // Declaring strength buttons
    private Button buttonsd_normal;
    private Button buttonsd_strong;
                                                                                                    // This was found on the internet...
    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbindDrawables(findViewById(R.id.stength_desire_layout));
        System.gc();
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                                                                                                    //The super keyword is used to refer to the parent class in java
        super.onCreate(savedInstanceState);
                                                                                                    //How the activity actually looks is inside main.xml, inside the layout folder
        setContentView(R.layout.strength_desire);                                                   // Setting layout view
        tv_sd = (TextView) findViewById(R.id.tv_sd);                                                // Make activity instruction text
        bar = (SeekBar)findViewById(R.id.seekBar);                                                  // Make seekbar object
        bar.setOnSeekBarChangeListener(this);                                                       // Set seekbar listener.
        textProgress = (TextView)findViewById(R.id.textProgress);                                   // Make progress text
        continueButton = (Button)findViewById(R.id.continueButton);                                 // Make continue button
        buttonsd_light = (Button) findViewById(R.id.sd_light);                                      // Making strength buttons
        buttonsd_normal = (Button) findViewById(R.id.sd_normal);
        buttonsd_strong = (Button) findViewById(R.id.sd_strong);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (progress <= 20) {
            textProgress.setText("Light");                                                           // Changes text to mild if slider within lower 1/3
        }
        if (progress > 20 && progress <= 40) {
            textProgress.setText("Mild");                                                         // Changes text to medium if slider within middle 1/3
        }
        if (progress > 40 && progress <= 60) {
            textProgress.setText("Normal");                                                         // Changes text to medium if slider within middle 1/3
        }
        if (progress > 60 && progress <= 80) {
            textProgress.setText("Strong");                                                         // Changes text to medium if slider within upper 1/3
        }
        if (progress > 80 && progress <= 100) {
            textProgress.setText("Bitter");                                                         // Changes text to medium if slider within middle 1/3
        }
        float temp = (float) progress;
        strengthDesire = 0.75f + temp/200;                                                           // Adjusts strengthDesire variable based on seekbar progress
        SharedPreferences prefs = this.getSharedPreferences("com.example.app",                      // Used for sharing data between activities
                Context.MODE_PRIVATE);
        prefs.edit().putFloat("com.example.app.strength", strengthDesire).apply();
    }

    public void onStartTrackingTouch(SeekBar seekBar) {                                             // Required for some reason
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        seekBar.setSecondaryProgress(seekBar.getProgress());                                        // Set the shade of the previous value.
    }

    public void lightBtnClk(View v)                                                                 // Ran when lightBtnClk pressed
    {
        strengthDesire = 0.75f;
        SharedPreferences prefs = this.getSharedPreferences("com.example.app",                      // Used for sharing data between activities
                Context.MODE_PRIVATE);
        prefs.edit().putFloat("com.example.app.strength", strengthDesire).apply();                  // This passes the correct arguments to the next screen and launches the next activity
        launchNextActivity();
    }

    public void mediumBtnClk(View v)                                                                // Ran when mediumBtnClk pressed
    {
        strengthDesire = 1.0f;
        SharedPreferences prefs = this.getSharedPreferences("com.example.app",                      // Used for sharing data between activities
                Context.MODE_PRIVATE);
        prefs.edit().putFloat("com.example.app.strength", strengthDesire).apply();                  // This passes the correct arguments to the next screen and launches the next activity
        launchNextActivity();
    }

    public void bitterBtnClk(View v)                                                                // Ran when strongBtnClk pressed
    {
        strengthDesire = 1.25f;
        SharedPreferences prefs = this.getSharedPreferences("com.example.app",                      // Used for sharing data between activities
                Context.MODE_PRIVATE);
        prefs.edit().putFloat("com.example.app.strength", strengthDesire).apply();                  // This passes the correct arguments to the next screen and launches the next activity
        launchNextActivity();
    }

    public void continueButtonClk(View v)                                                           // Ran when continueButton pressed
    {
        SharedPreferences prefs = this.getSharedPreferences("com.example.app",                      // Used for sharing data between activities
                Context.MODE_PRIVATE);
        prefs.edit().putFloat("com.example.app.strength", strengthDesire).apply();                  // This passes the correct arguments to the next screen and launches the next activity
        launchNextActivity();
    }

    private void launchNextActivity()
    {
        Intent nextActivity = new Intent(StrengthDesire.this, Timer.class);
                                                                                                    /*The intent class represents an action is used to "load" activities into a variable so they can be passed in and launched from
                                                                                                     * the startActivity method. Basic intents take two arguments, the current class(.java) and the class(.java) that the app will move to
                                                                                                     *  The line below initializes an Intent named resultActivity and passes in (Main.this,Result.class) much like the this-> pointer in C++,
                                                                                                     *  the this keyword in java is used by classes to reference themselves*/
                                                                                                    // Launches the new activity
        startActivity(nextActivity);
    }
}
