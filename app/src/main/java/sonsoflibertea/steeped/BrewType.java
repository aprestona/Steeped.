package sonsoflibertea.steeped;

/**
 * Created by Jose on 4/2/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BrewType extends Activity implements OnClickListener {
    // This is the backend of the tea_type layout for the selection of tea type activity
    public static final String TAG_BREWTYPE = "type";
    public static final String TAG_TIME = "time";


    private TextView tv_BrewType;
    private Button buttonInfusor;
    private Button buttonKettle;
    private Button buttonPitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //The super keyword is used to refer to the parent class in java
        super.onCreate(savedInstanceState);

        //How the activity actually looks is inside main.xml, inside the layout folder
        setContentView(R.layout.brew_type);

        //The buttons have parameters corresponding to the IDs in Main.xml

        tv_BrewType = (TextView) findViewById(R.id.tv_BrewType);

        buttonInfusor = (Button) findViewById(R.id.btn_infuser);
        buttonKettle = (Button) findViewById(R.id.btn_kettle);
        buttonPitcher = (Button) findViewById(R.id.btn_pitcher);


		/*The buttons now have onClickListeners set, a method/function of the button class
		 * to start a new activity/intent when pressed. In this case, pressing a button
		 * will go to the results page.
		 * */

        buttonInfusor.setOnClickListener(this);
        buttonKettle.setOnClickListener(this);
        buttonPitcher.setOnClickListener(this);

    }

    @Override
	/*onClick is what is called when the buttons are pressed and they take in Views as arguments
	 * as buttons are children of the view class, buttons can polymorphically be passed in. The button
	 * that called the onClick is automatically fed in*/
    public void onClick(View v) {
        String brewType = ""; // Type of tea entered.. not sure if going to be used yet..
        int time2steep = 0;


        //The switch statements grab the id values of the button pressed and calculates the tip accordingly
        switch (v.getId()) {

            case R.id.btn_infuser: {
                brewType = "Infusor";
                time2steep = 120;

                break;
            }
            case R.id.btn_kettle: {
                brewType = "Kettle";
                time2steep = 120;

                break;
            }
            case R.id.btn_pitcher: {
                brewType = "Pitcher";
                time2steep = 120;

                break;
            }

            default: {
                break;
            }
        }
        // This passes the correct arguments to the next screen and launches the next activity
      launchNextActivity(brewType, time2steep);
    }
    private void launchNextActivity(String brewType, int time2steep )
    {

		/*The intent class represents an action is used to "load" activities into a variable so they can be passed in and launched from
		 * the startActivity method. Basic intents take two arguments, the current class(.java) and the class(.java) that the app will move to
		 *  The line below initializes an Intent named resultActivity and passes in (Main.this,Result.class) much like the this-> pointer in C++,
		 *  the this keyword in java is used by classes to reference themselves*/
        Intent nextActivity = new Intent(BrewType.this, StrengthDesire.class);

		/*Since this method is private, if we want the Result Activity/class to access it's members (the strings TAG_TIP and TAG_GRAND_TOTAL),
		 *we can "push" members from the Main Acivity/class to Result, much like how a friend function can "pull" private members from objects
		*/
        nextActivity.putExtra(TAG_BREWTYPE, brewType);
       nextActivity.putExtra(TAG_TIME, time2steep);


//        Launches the new activity
        startActivity(nextActivity);
    }
}

