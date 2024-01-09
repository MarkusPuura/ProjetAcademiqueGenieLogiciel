package Modele.proj;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ButtonsProjectile {
    BufferedImage ameliorateB;
    BufferedImage sellB;
    BufferedImage returnB;

    public ButtonsProjectile() {
        GetImage();
    }

    public void GetImage() {
        try {
            ameliorateB = ImageIO
                    .read(getClass().getResourceAsStream("../../images/boutonsProjectileSelectionne/BoutonFleche.png"));
            sellB = ImageIO
                    .read(getClass().getResourceAsStream("../../images/boutonsProjectileSelectionne/BoutonDollar.png"));
            returnB = ImageIO
                    .read(getClass().getResourceAsStream("../../images/boutonsProjectileSelectionne/BoutonSortie.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getAmeliorateB() {
        return this.ameliorateB;
    }

    public BufferedImage getSellB() {
        return this.sellB;
    }

    public BufferedImage getReturnB() {
        return this.returnB;
    }
}
