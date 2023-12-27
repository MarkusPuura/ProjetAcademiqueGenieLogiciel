import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
public class Projectile {
    private int price;
    private int damage;
    private int speed;
    int x, y;
    private boolean active;
    private int radius;
    private Color color;

    public BufferedImage image;
    private Monstres target;

    public Projectile(int price , int damage, int speed, int x, int y,int radius){
        this.price=price;
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.radius = radius;
        //this.target = null;


        this.active = true;
    }
    public int getRadius(){
        return radius;
    }
    public Monstres getTarget() {
        return this.target;
    }

    public int getPrice() {
        return price;
    }
    public double getSpeed() {
        return speed;
    }
    public int getDamage() {
        return damage;
    }
    

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isActive() {
        return active;
    }
    public void setTarget(Monstres target) {
        this.target = target;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setPosition(int x, int y){
        this.x =x;
        this.y = y;
    }

    

    public void setActive(boolean active) {
        this.active = active;
    }
    public void updateTarget(Monstres monstre) {
       // System.out.println(this.target);

        if (this.target == null || monstre.HP <=0) {
            //System.out.println(this.target == null );

            //System.out.println("updateMonstre");
            this.target = monstre;
        }
    }
    public boolean checkInRange(Monstres monstre) {
        double distance = Math.sqrt(Math.pow(this.getX() - monstre.getX(), 2) + Math.pow(this.getY() - monstre.getY(), 2));
        return distance <= this.getRadius();
    }
    public void giveDmageToMonster(Monstres monstre) {
        System.out.println(this.getDamage());
        if(monstre != null) {
            monstre.looseLife(this.getDamage());
            System.out.println("Monster HP after hit: " + monstre.HP);
        }
    }
    public boolean hasEnoughMoney(Kama k){
        return this.getPrice() <= k.portefeuille;

    }


    public boolean correctPlacement(int x, int y, int TailleCarre,int LargeurEcran, int HauteurEcran ){
        int sizeTours1 = TailleCarre*2;
        return x >= 0 && y >= 0 && x + sizeTours1 <= LargeurEcran && y + sizeTours1 <= HauteurEcran;


    }


}
