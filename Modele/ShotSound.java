package Modele;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

public class ShotSound {
    private static Clip shotSound;
    /// private static String path;

    public ShotSound() {
    }

    public static void shotSound(String path) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    Musique.class.getResourceAsStream(path));

            AudioFormat format = audioInputStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);
            shotSound = (Clip) AudioSystem.getLine(info);

            shotSound.open(audioInputStream);

            FloatControl gainControl = (FloatControl) shotSound.getControl(FloatControl.Type.MASTER_GAIN);

            float volume = -30.0f;
            gainControl.setValue(volume);

            shotSound.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopperMusique() {
        if (shotSound != null && shotSound.isRunning()) {
            shotSound.stop();
            shotSound.close();
        }
    }
}
