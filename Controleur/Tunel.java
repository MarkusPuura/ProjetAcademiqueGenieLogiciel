package Controleur;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Tunel {
    private static Tunel instance; // Instance unique de Tunnel
    public int x1;
    public int y1;
    public int x2;
    public int y2;
    public int vies = 5;
    public BufferedImage Imagechateau, Imagechateau2;
    public BufferedImage vies0, vies1, vies2, vies3, vies4, vies5;
    public BufferedImage Imagevies;

    public Tunel(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        GetImage();
    }

    public static Tunel getInstance(int x1, int y1, int x2, int y2) {
        if (instance == null) {
            instance = new Tunel(x1, y1, x2, y2);
        }
        return instance;
    }

    public void GetImage(){
        try{
            Imagechateau = ImageIO.read(getClass().getResourceAsStream("../images/tours/chateau.png"));
            Imagechateau2 = ImageIO.read(getClass().getResourceAsStream("../images/tours/chateau2.png"));
            vies0 = ImageIO.read(getClass().getResourceAsStream("../images/tours/0vies.png"));
            vies1 = ImageIO.read(getClass().getResourceAsStream("../images/tours/1vie.png"));
            vies2 = ImageIO.read(getClass().getResourceAsStream("../images/tours/2vies.png"));
            vies3 = ImageIO.read(getClass().getResourceAsStream("../images/tours/3vies.png"));
            vies4 = ImageIO.read(getClass().getResourceAsStream("../images/tours/4vies.png"));
            vies5 = ImageIO.read(getClass().getResourceAsStream("../images/tours/5vies.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void updateVies(){
        if (this.vies == 5){
            this.Imagevies = vies5;
        }
        if (this.vies == 4){
            this.Imagevies = vies4;
        }
        if (this.vies == 3){
            this.Imagevies = vies3;
        }
        if (this.vies == 2){
            this.Imagevies = vies2;
        }
        if (this.vies == 1){
            this.Imagevies = vies1;
        }
        if (this.vies == 0){
            this.Imagevies = vies0;
        }
    }
    
}
