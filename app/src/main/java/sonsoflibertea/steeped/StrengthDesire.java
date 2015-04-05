package sonsoflibertea.steeped;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by a on 2015/4/6.
 */
public class StrengthDesire extends Activity implements View.OnClickListener {
    public static final String TAG_STRENGTHDESIRE = "type";

    private TextView tv_sd;
    private Button buttonsd_1;
    private Button buttonsd_2;
    private Button buttonsd_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //The super keyword is used to refer to the parent class in java
        super.onCreate(savedInstanceState);

        //How the activity actually looks is inside main.xml, inside the layout folder
        setContentView(R.layout.strength_desire);


        tv_sd = (TextView) findViewById(R.id.tv_sd);

        buttonsd_1 = (Button) findViewById(R.id.sd_1);
        buttonsd_2 = (Button) findViewById(R.id.sd_2);
        buttonsd_3 = (Button) findViewById(R.id.sd_3);


		/*The buttons now have onClickListeners set, a method/function of the button class
		 * to start a new activity/intent when pressed. In this case, pressing a button
		 * will go to the results page.
		 * */

        buttonsd_1.setOnClickListener(this);
        buttonsd_2.setOnClickListener(this);
        buttonsd_3.setOnClickListener(this);

    }

    @Override
	/*onClick is what is called when the buttons are pressed and they take in Views as arguments
	 * as buttons are children of the view class, buttons can polymorphically be passed in. The button
	 * that called the onClick is automatically fed in*/
    public void onClick(View v) {
        String StrengthDesire = ""; // Type of strength desired.. not sure if going to be used yet..


        //The switch statements grab the id values of the button pressed
        switch (v.getId()) {

            case R.id.sd_1: {
                StrengthDesire = "1";

                break;
            }
            case R.id.sb_2: {
                StrengthDesire = "2";

                break;
            }
            case R.id.sd_3: {
                StrengthDesire = "3";

                break;
            }

            default: {
                break;
            }
        }
        // This passes the correct arguments to the next screen and launches the next activity
//      launchNextActivity();
    }
    private void launchNextActivity(String StrengthDesire, int time2steep)
    {

    }
}
