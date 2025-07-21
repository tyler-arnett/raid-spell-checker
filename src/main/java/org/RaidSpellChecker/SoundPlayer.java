package org.RaidSpellChecker;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class SoundPlayer {
    private static Clip currentClip;

    public static synchronized void play(String fileName, int volumePercent) {
        try {
            // If a clip is already playing, skip playback
            if (currentClip != null && currentClip.isRunning()) {
                return;
            }

            try (InputStream audioSrc = SoundPlayer.class.getResourceAsStream("/sounds/" + fileName)) {
                if (audioSrc == null) {
                    System.err.println("Sound file not found: " + fileName);
                    return;
                }

                try (InputStream bufferedIn = new BufferedInputStream(audioSrc)) {
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                    Clip newClip = AudioSystem.getClip();
                    newClip.open(audioStream);

                    // Stop previous clip if needed
                    if (currentClip != null) {
                        currentClip.stop();
                        currentClip.close();
                    }

                    currentClip = newClip;

                    // Set volume
                    if (currentClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                        FloatControl volumeControl = (FloatControl) currentClip.getControl(FloatControl.Type.MASTER_GAIN);
                        float min = volumeControl.getMinimum();
                        float max = volumeControl.getMaximum();
                        float gain = (max - min) * (volumePercent / 100.0f) + min;
                        volumeControl.setValue(gain);
                    }

                    currentClip.start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}