package edu.ggc.king.morsecode;

import android.graphics.Color;
import android.media.AudioTrack;
import android.os.AsyncTask;
import android.util.Log;
import java.util.LinkedList;
import static android.content.ContentValues.TAG;

/**
 * Created by King on 11/3/2016.
 */

public class AsyncSub extends AsyncTask<String, Integer, Void> {
    String translate;
    int flashColor ;
    int oldColor ;
    AudioTrack sound;

    @Override
    protected Void doInBackground(String...params) {
        Log.v(TAG, "running doInBackground() ....");
        translate = params[0];
        int defer = 500;
        LinkedList<Signal> schedule = MorseCode.genOnOffSchedule(translate, defer);
        long begin = System.currentTimeMillis();

        while (!schedule.isEmpty()) {
            Signal signal = schedule.removeFirst();
            boolean arrival = false;
            while(!arrival)
                arrival = (System.currentTimeMillis() - begin) > signal.getOnset();
            updateUI(signal);
        }

        return null;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        flashColor = Color.rgb(255, 255, 255);
        oldColor = Color.rgb(2, 2, 2);
        if(values[0].equals(1)){
            MorseCode_Main.flashView.setBackgroundColor(flashColor);
        }
        else {
            MorseCode_Main.flashView.setBackgroundColor(oldColor);
        }
    }

    public void updateUI(Signal signal){

        if(signal.isOn()){
            publishProgress(1);
            int toneHrzt = 440;
            sound = AudioUtils.generateTone(toneHrzt, signal.getDuration());
            sound.play();
        }
        else{
            publishProgress(0);
            sound.release();
        }
        try {
            Thread.sleep(signal.getDuration());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}
