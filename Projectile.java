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

    public Projectile(int price , int damage, int speed, int x, int y,int radius){
        this.price=price;
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.radius = radius;


        this.active = true;
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

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, 26, 26); // Dessiner un cercle pour représenter le projectile
    }
    public void checkCollision(Monstres monstre) {
        double distance = Math.sqrt(Math.pow(this.getX() - monstre.getX(), 2) + Math.pow(this.getY() - monstre.getY(), 2));
        /*System.out.println("checkCollision : "+monstre.HP);
        System.out.println("x : "+monstre.x+" y : "+monstre.y);*/
        System.out.println("Distance : " + distance);
        System.out.println("Radius : " + this.radius);
        System.out.println(distance < this.radius);

        //System.out.println("x : " + this.getX()+" y : "+this.getY());


        if (distance < this.radius) {
            monstre.looseLife(this.getDamage());
            System.out.println("Monster HP after hit: " + monstre.HP);
            //this.setActive(false); // Désactivez le projectile après la collision
        }
    }


}
