import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tours1 extends Projectile {
    private boolean selected;
    private int numType;


    public Tours1(int price, int damage, int speed, int x, int y,int radius){
        super(price, damage, speed, x, y,radius);
        this.numType = 1;
        GetImage();
    }

    public void GetImage(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/tours/tours1.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public boolean isClickedAndSelectionnedTours1(int x,int y,int TailleCarre, int HauteurEcran){
        int xTours1 = 6*TailleCarre;
        int yTours1 = HauteurEcran - 3*TailleCarre;
        int sizeTours1 = TailleCarre*2;
        return x >= xTours1 && x <= xTours1 + sizeTours1 && y >= yTours1 && y <= yTours1 + sizeTours1;

    }

    public boolean canAffordAndClickTours1(int x, int y, int TailleCarre, int HauteurEcran, Kama k) {
        boolean isClicked = isClickedAndSelectionnedTours1(x, y, TailleCarre, HauteurEcran);
        boolean enoughMoney = hasEnoughMoney(k);
        return isClicked && enoughMoney;
    }


    public Tours1 getSelectedTower(int x, int y, int TailleCarre, int HauteurEcran, Kama k) {
        boolean isClicked = isClickedAndSelectionnedTours1(x, y, TailleCarre, HauteurEcran);
        boolean enoughMoney = hasEnoughMoney(k);

        if (isClicked && enoughMoney) {
            selected = true;
            return this;
        } else {

            selected = false;
            return null;

        }
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    @Override
    public String toString() {
        return "Tours1: [Price=" + getPrice() + ", Damage=" + getDamage() + ", Speed=" + getSpeed() + ", X=" + getX() + ", Y=" + getY() + "]";
    }

    public int getNumType() {
        return numType;
    }

    public void setNumType(int numType) {
        this.numType = numType;
    }
}
