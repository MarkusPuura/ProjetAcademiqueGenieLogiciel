import java.awt.Color;
import java.awt.Graphics;
public class Projectile {
    private int price;
    private int damage;
    private int speed;
    private int x, y;
    private int directionX, directionY;
    private boolean active;
    private Color color;
    public Projectile(int price , int damage, int speed, int x, int y, int directionX, int directionY){
        this.price=price;
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;


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
    public double getDirectionX() {
        return directionX;
    }
    public double getDirectionY() {
        return directionY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
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

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, 26, 26); // Dessiner un cercle pour représenter le projectile
    }



}
