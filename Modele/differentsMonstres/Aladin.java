package Modele.differentsMonstres;

import java.io.IOException;
import javax.imageio.ImageIO;

import Modele.Monstres;

public class Aladin extends Monstres {
    public Aladin(int HP, double vitesse, int or, int x, int y) {
        super(HP, vitesse, or, x, y, 1);
        GetImage();
    }

    public void GetImage() {
        try {
            bas1 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/aladin_bas1.png"));
            bas2 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/aladin_bas2.png"));
            haut1 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/aladin_haut1.png"));
            haut2 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/aladin_haut2.png"));
            gauche1 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/aladin_gauche1.png"));
            gauche2 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/aladin_gauche2.png"));
            droite1 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/aladin_droite1.png"));
            droite2 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/aladin_droite2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
