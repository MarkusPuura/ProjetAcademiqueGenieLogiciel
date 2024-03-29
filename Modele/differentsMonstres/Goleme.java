package Modele.differentsMonstres;

import java.io.IOException;
import javax.imageio.ImageIO;

import Modele.Monstres;

public class Goleme extends Monstres {
    public Goleme(int HP, double vitesse, int or, int x, int y) {
        super(HP, vitesse, or, x, y, 1);
        GetImage();
    }

    public void GetImage() {
        try {
            bas1 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/goleme_bas1.png"));
            bas2 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/goleme_bas2.png"));
            haut1 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/goleme_haut1.png"));
            haut2 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/goleme_haut2.png"));
            gauche1 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/goleme_gauche1.png"));
            gauche2 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/goleme_gauche2.png"));
            droite1 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/goleme_droite1.png"));
            droite2 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/goleme_droite2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
