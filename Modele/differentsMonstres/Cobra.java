package Modele.differentsMonstres;

import java.io.IOException;
import javax.imageio.ImageIO;

import Modele.Monstres;

public class Cobra extends Monstres {
    public Cobra(int HP, double vitesse, int or, int x, int y) {
        super(HP, vitesse, or, x, y, 0);
        GetImage();
    }

    public void GetImage() {
        try {
            bas1 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/cobra_bas1.png"));
            bas2 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/cobra_bas2.png"));
            haut1 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/cobra_haut1.png"));
            haut2 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/cobra_haut2.png"));
            gauche1 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/cobra_gauche1.png"));
            gauche2 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/cobra_gauche2.png"));
            droite1 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/cobra_droite1.png"));
            droite2 = ImageIO.read(getClass().getResourceAsStream("../../images/personnages/cobra_droite2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
