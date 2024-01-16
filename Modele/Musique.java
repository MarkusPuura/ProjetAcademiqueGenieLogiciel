package Modele;

import javax.sound.sampled.*;

public class Musique {
    private static Clip clip;

    public Musique() {
        jouerMusiqueEnBoucle("../sons/musique.wav");
    }

    public void jouerMusiqueEnBoucle(String cheminMusique) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    Musique.class.getResourceAsStream(cheminMusique));

            AudioFormat format = audioInputStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);

            clip.open(audioInputStream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            float volume = -50.0f;
            gainControl.setValue(volume);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopperMusique() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

}
