package main;

public class Projectile {
    private int price;
    private int damage;
    private double speed;
    private double x, y;
    private double directionX, directionY;
    private boolean active;
    public Projectile(int price , int damage, double speed, double x, double y, double directionX, double directionY){
        this.price=price;
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;
        double distance = Math.sqrt(Math.pow(directionX - x, 2) + Math.pow(directionY - y, 2));
        this.directionX = (directionX - x) / distance;
        this.directionY = (directionY - y) / distance;

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
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setDirectionX(double directionX) {
        this.directionX = directionX;
    }

    public void setDirectionY(double directionY) {
        this.directionY = directionY;
    }

    public void setActive(boolean active) {
        this.active = active;
    }





}
