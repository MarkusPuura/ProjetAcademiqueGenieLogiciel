import java.io.IOException;
import javax.imageio.ImageIO;

public class Serpent extends Monstres{
    public Serpent(int HP, double vitesse, int kama, int x, int y){
        super(HP, vitesse, kama, x, y);
        GetImage();
    }

    public void GetImage(){
        try{
            bas1 = ImageIO.read(getClass().getResourceAsStream("/personnages/serpent_bas1.png"));
            bas2 = ImageIO.read(getClass().getResourceAsStream("/personnages/serpent_bas2.png"));
            haut1 = ImageIO.read(getClass().getResourceAsStream("/personnages/serpent_haut1.png"));
            haut2 = ImageIO.read(getClass().getResourceAsStream("/personnages/serpent_haut2.png"));
            gauche1 = ImageIO.read(getClass().getResourceAsStream("/personnages/serpent_gauche1.png"));
            gauche2 = ImageIO.read(getClass().getResourceAsStream("/personnages/serpent_gauche2.png"));
            droite1 = ImageIO.read(getClass().getResourceAsStream("/personnages/serpent_droite1.png"));
            droite2 = ImageIO.read(getClass().getResourceAsStream("/personnages/serpent_droite2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
