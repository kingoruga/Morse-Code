package edu.ggc.king.morsecode;


import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;



public class AudioUtils {


        // copied from https://gist.github.com/slightfoot/6330866

        // Usage:
        //    AudioTrack tone = generateTone(440, 250);
        //    tone.play();
        //
        public static AudioTrack generateTone(double freqHz, int durationMs) {
            int count = (int)(44100.0 * 2.0 * (durationMs / 1000.0)) & ~1;
            short[] samples = new short[count];
            for(int i = 0; i < count; i += 2){
                short sample = (short)(Math.sin(2 * Math.PI * i / (44100.0 / freqHz)) * 0x7FFF);
                samples[i + 0] = sample;
                samples[i + 1] = sample;
            }
            AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                    AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT,
                    count * (Short.SIZE / 8), AudioTrack.MODE_STATIC);
            track.write(samples, 0, count);
            return track;
        }



}
