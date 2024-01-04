
import java.awt.image.BufferedImage;


public abstract class Monstres {
    int HP;
    double vitesse;
    int kama;
    int x, y;
    int bebes;
    int fin = 0;    //si il est arrivé jusqu'au chateau

    public Monstres(int HP, double vitesse, int kama, int x, int y, int bebes){
        this.HP = HP;
        this.vitesse = vitesse;
        this.kama = kama;
        this.x = x;
        this.y = y;
        this.bebes = bebes;     // si retourne des bebes quand meurt
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public BufferedImage bas1, bas2, haut1, haut2, gauche1, gauche2, droite1, droite2;
    public String direction;
    public BufferedImage image;
    public int animation = 0;

    


    public void updateMonstre(int TailleCarre, int HauteurEcran, int LargeurEcran){
        if (this.y == TailleCarre*2){
            if (this.x < LargeurEcran - TailleCarre*3){
                this.x+=this.vitesse; this.direction = "droite";
            }
            else{this.y+=this.vitesse; this.direction = "bas";}
        }
        if (this.x == LargeurEcran - TailleCarre*3){
            if (this.y < HauteurEcran - 6*TailleCarre){
                this.y+=this.vitesse;
                this.direction = "bas";
            }
            else{this.x-=this.vitesse; this.direction = "gauche";}
        }
        if (this.y == HauteurEcran - 6*TailleCarre){
            if (this.x > 17*TailleCarre){
                this.x-=this.vitesse;
                this.direction = "gauche";
            }
            else{this.y-=this.vitesse; this.direction = "haut";}
        }
        if (this.x == 17*TailleCarre && this.direction == "haut"){
            if (this.y > 8*TailleCarre){
                this.y-=this.vitesse;
                this.direction = "haut";
            }
            else{this.x-=this.vitesse; this.direction = "gauche";}
        }
        if (this.y == 8*TailleCarre && this.direction == "gauche"){
            if (this.x > 5*TailleCarre){
                this.x-=this.vitesse;
                this.direction = "gauche";
            }
            else{   // le monstre est arrivé au chateau
                this.fin = 1;
            }
        }
    }
    
    public void deplacementZombie(){
        if (this.direction == "bas"){
            if (this.animation < 10){
                this.image = this.bas1;
                this.animation++;
            }
            else{
                this.image = this.bas2;
                this.animation++;
                if (this.animation > 20){
                    this.animation = 0;
                }
            }
        }
        if (this.direction == "haut"){
            if (this.animation < 10){
                this.image = this.haut1;
                this.animation++;
            }
            else{
                this.image = this.haut2;
                this.animation++;
                if (this.animation > 20){
                    this.animation = 0;
                }
            }
        }
        if (this.direction == "gauche"){
            if (this.animation < 10){
                this.image = this.gauche1;
                this.animation++;
            }
            else{
                this.image = this.gauche2;
                this.animation++;
                if (this.animation > 20){
                    this.animation = 0;
                }
            }
        }
        if (this.direction == "droite"){
            if (this.animation < 10){
                this.image = this.droite1;
                this.animation++;
            }
            else{
                this.image = this.droite2;
                this.animation++;
                if (this.animation > 20){
                    this.animation = 0;
                }
            }
        }
    }

    public int looseLife(int damage){
        System.out.println(this.HP+" - "+damage +"=");
        this.HP -= damage;
        System.out.println(this.HP);
        return this.HP;
    }

    @Override
    public String toString() {
        return "Monstre{" +
                "HP=" + this.HP +
                ", vitesse=" + this.vitesse +
                ", kama=" + this.kama +
                ", x=" + this.x +
                ", y=" + this.y +
                ", fin=" + this.fin +
                ", direction='" + this.direction + '\'' +
                '}';
    }
    public boolean isAlive (){
        return this.HP >0;
    }

}
