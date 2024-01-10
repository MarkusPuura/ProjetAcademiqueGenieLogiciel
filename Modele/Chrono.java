package Modele;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Chrono{
    public int min;
    public int sec;
    int secSurFPS;
    public BufferedImage image;


    public Chrono(){
        this.min = 0;
        this.sec = 30;
        this.secSurFPS = 0;
        GetImage();
    }

    public int UpdateChrono(int FPS){
        this.secSurFPS--;
        if (this.secSurFPS <= 0){
            this.secSurFPS = FPS;
            this.sec--;
            if (this.sec < 0){
                this.sec = 59;
                this.min--;
                if (this.min < 0){
                    this.min = 0;
                    this.sec = 0;
                    return 1;
                }
            }
        }
        return 0;
    }
    public void GetImage(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream("../images/sablier/sablier.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
