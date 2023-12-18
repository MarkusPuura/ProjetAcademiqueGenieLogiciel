import java.io.IOException;
import javax.imageio.ImageIO;

public class Tours1 extends Projectile {
    private boolean selected;

    public Tours1(int price, int damage, int speed, int x, int y,int radius){
        super(price, damage, speed, x, y,radius);
        GetImage();
    }
    public void afficherTours1(){
        System.out.println("Price : "+this.getPrice()+" damage : "+this.getDamage()+" speed : "+this.getSpeed()+" x : "+this.getX()+" y : "+this.getY());
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
    // Dans la classe Projectile



    public boolean hasEnoughMoney(Kama k){
        return this.getPrice() <= k.portefeuille;

    }
    public boolean canAffordAndClickTours1(int x, int y, int TailleCarre, int HauteurEcran, Kama k) {
        boolean isClicked = isClickedAndSelectionnedTours1(x, y, TailleCarre, HauteurEcran);
        boolean enoughMoney = hasEnoughMoney(k);
        return isClicked && enoughMoney;
    }

    public boolean correctPlacement(int x, int y, int TailleCarre,int LargeurEcran, int HauteurEcran ){
        int sizeTours1 = TailleCarre*2;
        return x >= 0 && y >= 0 && x + sizeTours1 <= LargeurEcran && y + sizeTours1 <= HauteurEcran;


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
}
