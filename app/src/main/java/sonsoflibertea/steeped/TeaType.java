package sonsoflibertea.steeped;

/**
 * Created by akshay on 4/2/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;
import android.content.SharedPreferences;



public class TeaType extends Activity implements OnClickListener {
    // This is the backend of the tea_type layout for the selection of tea type activity
    public static final String TAG_TEATYPE = "type";
    public static final String TAG_TIME = "time";

    private TextView tvTeaType;
    private Button buttonW;
    private Button buttonY;
    private Button buttonG;
    private Button buttonO;
    private Button buttonB;
    private Button buttonH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //The super keyword is used to refer to the parent class in java
        super.onCreate(savedInstanceState);

        //How the activity actually looks is inside main.xml, inside the layout folder
        setContentView(R.layout.tea_type);

        //The buttons have parameters corresponding to the IDs in tea_type.xml

        tvTeaType = (TextView) findViewById(R.id.tv_TeaType);

        buttonW = (Button) findViewById(R.id.btn_white);
        buttonY = (Button) findViewById(R.id.btn_yellow);
        buttonG = (Button) findViewById(R.id.btn_green);
        buttonO = (Button) findViewById(R.id.btn_oolong);
        buttonB = (Button) findViewById(R.id.btn_black);
        buttonH = (Button) findViewById(R.id.btn_herbal);

		/*The buttons now have onClickListeners set, a method/function of the button class
		 * to start a new activity/intent when pressed. In this case, pressing a button
		 * will go to the results page.
		 * */

        buttonW.setOnClickListener(this);
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
        SharedPreferences prefs = this.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);

        //The switch statements grab the id values of the button pressed and calculates the tip accordingly
        switch (v.getId()) {

            case R.id.btn_white: {
                teaType = "White";
                time2steep = 120;
                break;
            }
            case R.id.btn_yellow: {
                teaType = "Yellow";
                time2steep = 120;
                break;
            }
            case R.id.btn_green: {
                teaType = "Green";
                time2steep = 120;
                break;
            }
            case R.id.btn_oolong: {
                teaType = "Oolong";
                time2steep = 200;
                break;
            }
            case R.id.btn_black: {
                teaType = "Black";
                time2steep = 200;
                break;
            }
            case R.id.btn_herbal: {
                teaType = "Herbal";
                time2steep = 320;
                break;
            }
            default: {
                break;
            }
        }
        // This passes the correct arguments to the next screen and launches the next activity
        prefs.edit().putInt("com.example.app.base_time", time2steep).apply();
        launchNextActivity(teaType,time2steep);
    }
    private void launchNextActivity(String teaType, int time2steep)
    {

		/*The intent class represents an action is used to "load" activities into a variable so they can be passed in and launched from
		 * the startActivity method. Basic intents take two arguments, the current class(.java) and the class(.java) that the app will move to
		 *  The line below initializes an Intent named resultActivity and passes in (Main.this,Result.class) much like the this-> pointer in C++,
		 *  the this keyword in java is used by classes to reference themselves*/
        Intent nextActivity = new Intent(TeaType.this, BrewType.class);

		/*Since this method is private, if we want the Result Activity/class to access it's members (the strings TAG_TIP and TAG_GRAND_TOTAL),
		 *we can "push" members from the Main Acivity/class to Result, much like how a friend function can "pull" private members from objects
		*/
        nextActivity.putExtra(TAG_TEATYPE, teaType);
        nextActivity.putExtra(TAG_TIME, time2steep);


//        Launches the new activity
        startActivity(nextActivity);
    }
}

