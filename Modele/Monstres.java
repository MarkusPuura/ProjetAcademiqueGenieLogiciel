package Modele;

import java.awt.image.BufferedImage;

public abstract class Monstres {
    public int HP;
    public double vitesse;
    public int or;
    public int x, y;
    public int bebes;
    public int fin = 0; // si il est arrivé jusqu'au chateau
    public double initVitesse;

    public Monstres(int HP, double vitesse, int or, int x, int y, int bebes) {
        this.HP = HP;
        this.vitesse = vitesse;
        this.or = or;
        this.x = x;
        this.y = y;
        this.bebes = bebes; // si retourne des bebes quand meurt
        this.initVitesse = vitesse;
    }

    public void resetMonsterSpeed() {
        this.vitesse = this.initVitesse;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public BufferedImage bas1, bas2, haut1, haut2, gauche1, gauche2, droite1, droite2;
    public String direction;
    public BufferedImage image;
    public int animation = 0;

    public void updateMonstre(int TailleCarre, int HauteurEcran, int LargeurEcran, int lvl) {
        if (lvl == 1) {
            if (this.y == TailleCarre * 2) {
                if (this.x < LargeurEcran - TailleCarre * 3) {
                    this.x += this.vitesse;
                    this.direction = "droite";
                } else {
                    this.x = LargeurEcran - TailleCarre * 3;
                    this.y += this.vitesse;
                    this.direction = "bas";
                }
            }
            if (this.x == LargeurEcran - TailleCarre * 3) {
                if (this.y < HauteurEcran - 6 * TailleCarre) {
                    this.y += this.vitesse;
                    this.direction = "bas";
                } else {
                    this.y = HauteurEcran - 6 * TailleCarre;
                    this.x -= this.vitesse;
                    this.direction = "gauche";
                }
            }
            if (this.y == HauteurEcran - 6 * TailleCarre) {
                if (this.x > 17 * TailleCarre) {
                    this.x -= this.vitesse;
                    this.direction = "gauche";
                } else {
                    this.x = 17 * TailleCarre;
                    this.y -= this.vitesse;
                    this.direction = "haut";
                }
            }
            if (this.x == 17 * TailleCarre && this.direction == "haut") {
                if (this.y > 8 * TailleCarre) {
                    this.y -= this.vitesse;
                    this.direction = "haut";
                } else {
                    this.y = 8 * TailleCarre;
                    this.x -= this.vitesse;
                    this.direction = "gauche";
                }
            }
            if (this.y == 8 * TailleCarre && this.direction == "gauche") {
                if (this.x > 5 * TailleCarre) {
                    this.x -= this.vitesse;
                    this.direction = "gauche";
                } else { // le monstre est arrivé au chateau
                    this.fin = 1;
                }
            }
        }
        if (lvl == 2) {
            if (this.y == TailleCarre * 2) {
                if (this.x < TailleCarre * 15) {
                    this.x += this.vitesse;
                    this.direction = "droite";
                } else {
                    this.x = TailleCarre * 15;
                    this.y += this.vitesse;
                    this.direction = "bas";
                }
            }
            if (this.x == TailleCarre * 15) {
                if (this.y < HauteurEcran - 9 * TailleCarre) {
                    this.y += this.vitesse;
                    this.direction = "bas";
                } else {
                    this.y = HauteurEcran - 9 * TailleCarre;
                    this.x -= this.vitesse;
                    this.direction = "bas";
                }
            }
            if (this.y == HauteurEcran - TailleCarre * 9) {
                if (this.x < TailleCarre * 30) {
                    this.x += this.vitesse;
                    this.direction = "droite";
                } else {
                    this.fin = 1;
                }
            }

        }
    }

    public void deplacementZombie() {
        if (this.direction == "bas") {
            if (this.animation < 10) {
                this.image = this.bas1;
                this.animation++;
            } else {
                this.image = this.bas2;
                this.animation++;
                if (this.animation > 20) {
                    this.animation = 0;
                }
            }
        }
        if (this.direction == "haut") {
            if (this.animation < 10) {
                this.image = this.haut1;
                this.animation++;
            } else {
                this.image = this.haut2;
                this.animation++;
                if (this.animation > 20) {
                    this.animation = 0;
                }
            }
        }
        if (this.direction == "gauche") {
            if (this.animation < 10) {
                this.image = this.gauche1;
                this.animation++;
            } else {
                this.image = this.gauche2;
                this.animation++;
                if (this.animation > 20) {
                    this.animation = 0;
                }
            }
        }
        if (this.direction == "droite") {
            if (this.animation < 10) {
                this.image = this.droite1;
                this.animation++;
            } else {
                this.image = this.droite2;
                this.animation++;
                if (this.animation > 20) {
                    this.animation = 0;
                }
            }
        }
    }

    public int looseLife(int damage) {
        this.HP -= damage;
        return this.HP;
    }

    public void slowerMonster(double slow) {
        // double vitesseInit = this.vitesse;
        this.vitesse = slow;

    }

    @Override
    public String toString() {
        return "Monstre{" +
                "HP=" + this.HP +
                ", vitesse=" + this.vitesse +
                ", or=" + this.or +
                ", x=" + this.x +
                ", y=" + this.y +
                ", fin=" + this.fin +
                ", direction='" + this.direction + '\'' +
                '}';
    }

    public boolean isAlive() {
        return this.HP > 0;
    }

}
