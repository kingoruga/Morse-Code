package edu.ggc.king.morsecode;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static edu.ggc.king.morsecode.MorseCode.map;


public class MorseCode_Main extends AppCompatActivity {
    //Button button;
    Intent intent;
    EditText editView;
    String userInput;
    static TextView flashView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morsecode__main);

       // button = (Button) findViewById(R.id.abtBtn);
        intent = new Intent(MorseCode_Main.this, AboutScreen.class);

        editView = (EditText) findViewById(R.id.editView1);
        flashView = (TextView) findViewById(R.id.flashView);


    }
    /**
     * Method: showAbout()
     *
     * This method starts a new activity which shows the about screen.
     *
     * @param view
     **/

    public void showAbout(View view){
            startActivity(intent);
        }

    /**
     *
     * Method: transmit()
     *
     * Button to transmits user input to morse code
     *
     * @param view
     */
    public void transmit (View view){
        userInput = editView.getText().toString();
        AsyncSub work = new AsyncSub();
        work.execute(userInput);
    }





}
