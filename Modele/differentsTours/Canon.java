package Modele.differentsTours;

import javax.imageio.ImageIO;

import Controleur.ListeMonstresVivants;
import Modele.Or;
import Modele.Monstres;
import Modele.Projectile;
import Modele.proj.ShootStrategy;
import Vue.ZonedShoot;

import java.awt.*;
import java.io.IOException;

public class Canon extends Projectile {
    private boolean selected;
    private int numType;
    private ShootStrategy shootStrategy;

    public Canon(int price, int damage, int speed, int x, int y, int radius) {
        super(price, damage, speed, x, y, radius);
        this.numType = 2;
        this.shootStrategy = new ZonedShoot();

        GetImage();
    }

    public void afficherCanon() {
        System.out.println("Price : " + this.getPrice() + " damage : " + this.getDamage() + " speed : "
                + this.getSpeed() + " x : " + this.getX() + " y : " + this.getY());
    }

    public void GetImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../../images/tours/CannonAB.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("../../images/tours/CannonAB2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isClickedAndSelectionnedTours1(int x, int y, int TailleCarre, int HauteurEcran) {
        int xTours1 = 6 * TailleCarre;
        int yTours1 = HauteurEcran - 3 * TailleCarre;
        int sizeTours1 = TailleCarre * 2;
        return x >= xTours1 && x <= xTours1 + sizeTours1 && y >= yTours1 && y <= yTours1 + sizeTours1;

    }

    public void amelioration(int numAmelio) {
        this.setDamage(this.getDamage() + 1 * numAmelio);
        this.setSpeed(this.getSpeed() - 5 * numAmelio);
        // this.setRadius(this.getRadius() + 10);
    }

    public boolean canAffordAndClickTours1(int x, int y, int TailleCarre, int HauteurEcran, Or k) {
        boolean isClicked = isClickedAndSelectionnedTours1(x, y, TailleCarre, HauteurEcran);
        boolean enoughMoney = hasEnoughMoney(k);
        return isClicked && enoughMoney;
    }

    public Canon getSelectedTower(int x, int y, int TailleCarre, int HauteurEcran, Or k) {
        boolean affordAndClick = canAffordAndClickTours1(x, y, TailleCarre, HauteurEcran, k);

        if (affordAndClick) {
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
        return "Canon: [Price=" + getPrice() + ", Damage=" + getDamage() + ", Speed=" + getSpeed() + ", X=" + getX()
                + ", Y=" + getY() + "]";
    }

    public int getNumType() {
        return numType;
    }

    public void setNumType(int numType) {
        this.numType = numType;
    }

    public void tirer(Graphics2D gq, Monstres target, ListeMonstresVivants list, int distance) {
        shootStrategy.tirer(gq, this, target, list, distance);

    }
}
