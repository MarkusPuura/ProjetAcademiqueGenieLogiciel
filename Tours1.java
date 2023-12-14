import java.io.IOException;
import javax.imageio.ImageIO;

public class Tours1 extends Projectile {
    public Tours1(int price, int damage, int speed, int x, int y){
        super(price, damage, speed, x, y);
        GetImage();
    }

    public void GetImage(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/tours/tours1.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
