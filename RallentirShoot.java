import java.awt.*;

public class RallentirShoot implements ShootStrategy{

    @Override
    public void tirer(Graphics2D gq, Projectile projectile, Monstres target) {
        if(target != null && projectile.checkInRange(target)&&target.HP>0 ) {
            double initVitesse = target.vitesse;

            if(!projectile.getTirePas()) {
                System.out.println("actionTemp");
                gq.setColor(Color.WHITE);
                gq.drawLine(projectile.x + 32, projectile.y + 32, target.getX(), target.getY());
                target.vitesse = 5;
                System.out.println(target.vitesse);

                System.out.println("Monster HP after hit: " + target.HP);
                projectile.setTirePas(true);
            }
            System.out.println(target.vitesse+"----------");

            target.vitesse = initVitesse;

        }
        /*if(target != null ) {
            int vitesseVar = target.vitesse;
            int vitesseRalentis = target.vitesse=3;
            if(projectile.checkInRange(target)&&target.HP>0 && !projectile.getTirePas()){
                System.out.println(target.vitesse);
                target.vitesse =vitesseRalentis;
                System.out.println(target.vitesse);

                //target.looseLife(projectile.getDamage());

            }

            else {

                target.vitesse = vitesseVar;
                System.out.println(target.vitesse);
                projectile.setTarget(null);
                System.out.println(target);
                projectile.setTirePas(true);
            }
               // projectile.setTarget(null);





        }
        System.out.println("fini");*/



    }
}
