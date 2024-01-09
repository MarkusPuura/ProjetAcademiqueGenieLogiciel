package Modele;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Kama {         // si on rajoute pas d'autres propriétés en lien avec l'argent on pourra supprimer cette classe, juste
    public int portefeuille;       // faire un int portefeuille globale.
    public BufferedImage image;


    public Kama(){
        this.portefeuille = 1000;
        GetImage();
    }
    public void finPortefeuil(){
        this.portefeuille = 0;
    }

    void kamaUpdate(int taux){
        this.portefeuille += taux; 
    }
    public void buyProjectile(int price){
        this.portefeuille -= price;
    }
    public void GetImage(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream("../images/or/pieceOr.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}