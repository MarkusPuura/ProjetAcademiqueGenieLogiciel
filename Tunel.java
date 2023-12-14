
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Tunel {
    private static Tunel instance; // Instance unique de Tunnel
    int x;
    int y;
    int vies = 5;
    public BufferedImage Imagechateau;
    public BufferedImage vies0, vies1, vies2, vies3, vies4, vies5;
    public BufferedImage Imagevies;

    public Tunel(int x, int y) {
        this.x = x;
        this.y = y;
        GetImage();
    }

    public static Tunel getInstance(int x, int y) {
        if (instance == null) {
            instance = new Tunel(x, y);
        }
        return instance;
    }

    public void GetImage(){
        try{
            Imagechateau = ImageIO.read(getClass().getResourceAsStream("/tours/chateau.png"));
            vies0 = ImageIO.read(getClass().getResourceAsStream("/tours/0vies.png"));
            vies1 = ImageIO.read(getClass().getResourceAsStream("/tours/1vie.png"));
            vies2 = ImageIO.read(getClass().getResourceAsStream("/tours/2vies.png"));
            vies3 = ImageIO.read(getClass().getResourceAsStream("/tours/3vies.png"));
            vies4 = ImageIO.read(getClass().getResourceAsStream("/tours/4vies.png"));
            vies5 = ImageIO.read(getClass().getResourceAsStream("/tours/5vies.png"));
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
