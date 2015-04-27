package sonsoflibertea.steeped;

/**
 * Created by akshay on 4/2/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;
import android.content.SharedPreferences;



public class TeaType extends Activity implements OnClickListener {

    private TextView tvTeaType; // Initialize all the variable names for the text view and the buttons
    private Button buttonW;
    private Button buttonY;
    private Button buttonG;
    private Button buttonO;
    private Button buttonB;
    private Button buttonH;


    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbindDrawables(findViewById(R.id.tea_type_layout));
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
        setContentView(R.layout.tea_type);

        tvTeaType = (TextView) findViewById(R.id.tv_TeaType); // set variable to tv

        buttonW = (Button) findViewById(R.id.btn_white); // set the variables to the buttons by finding their ids
        buttonY = (Button) findViewById(R.id.btn_yellow);
        buttonG = (Button) findViewById(R.id.btn_green);
        buttonO = (Button) findViewById(R.id.btn_oolong);
        buttonB = (Button) findViewById(R.id.btn_black);
        buttonH = (Button) findViewById(R.id.btn_herbal);

		/*The buttons now have onClickListeners set, a method/function of the button class
		 * to start a new activity/intent when pressed. In this case, pressing a button
		 * will go to the results page.
		 * */

        buttonW.setOnClickListener(this); // set listeners for a button press
        buttonY.setOnClickListener(this);
        buttonG.setOnClickListener(this);
        buttonO.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonH.setOnClickListener(this);
    }

    @Override
	/*onClick is what is called when the buttons are pressed and they take in Views as arguments
	 * as buttons are children of the view class, buttons can polymorphically be passed in. The button
	 * that called the onClick is automatically fed in*/
    public void onClick(View v) {
        String teaType = ""; // Type of tea entered.. not sure if going to be used yet..
        int time2steep = 0; // Time for tea to steep in seconds
        int tempF = 0;
        int tempC = 0;
        SharedPreferences prefs = this.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);

        //The switch statements grab the id values of the button pressed and calculates the tip accordingly
        switch (v.getId()) {

            case R.id.btn_white: { // white button pressed
                teaType = "White";
                time2steep = 120;
                tempF = 170;
                tempC = 77;
                break;
            }
            case R.id.btn_yellow: { // yellow button pressed
                teaType = "Yellow";
                time2steep = 120;
                tempF = 165;
                tempC = 74;
                break;
            }
            case R.id.btn_green: { // green button pressed
                teaType = "Green";
                time2steep = 120;
                tempF = 175;
                tempC = 80;
                break;
            }
            case R.id.btn_oolong: { // oolong button pressed
                teaType = "Oolong";
                time2steep = 200;
                tempF = 190;
                tempC = 88;
                break;
            }
            case R.id.btn_black: { // black button pressed
                teaType = "Black";
                time2steep = 200;
                tempF = 200;
                tempC = 93;
                break;
            }
            case R.id.btn_herbal: { // herbal button pressed
                teaType = "Herbal";
                time2steep = 320;
                tempF = 208;
                tempC = 98;
                break;
            }
            default: {
                break;
            }
        }
        // This passes the correct arguments to the next screen and launches the next activity
        prefs.edit().putInt("com.example.app.base_time", time2steep).apply(); // set prefs and put in the base time
        prefs.edit().putInt("com.example.app.tempF",tempF).apply();
        prefs.edit().putInt("com.example.app.tempC",tempC).apply();
        launchNextActivity();
    }
    private void launchNextActivity()
    {

		/*The intent class represents an action is used to "load" activities into a variable so they can be passed in and launched from
		 * the startActivity method. Basic intents take two arguments, the current class(.java) and the class(.java) that the app will move to
		 *  The line below initializes an Intent named resultActivity and passes in (Main.this,Result.class) much like the this-> pointer in C++,
		 *  the this keyword in java is used by classes to reference themselves*/
        Intent nextActivity = new Intent(TeaType.this, StrengthDesire.class);

//        Launches the new activity
        startActivity(nextActivity);
    }
}

