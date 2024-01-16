package Vue;

import java.awt.*;

import Controleur.ListeMonstresVivants;
import Modele.Monstres;
import Modele.Projectile;
import Modele.ShotSound;
import Modele.proj.ShootStrategy;

public class RallentirShoot implements ShootStrategy {
    private boolean soundPlayed = false;

    @Override
    public void tirer(Graphics2D gq, Projectile projectile, Monstres target, ListeMonstresVivants list, int distance) {
        if (!soundPlayed) {
            ShotSound.shotSound("../sons/sorcier.wav");
            soundPlayed = true;
        }

        Monstres iterateur = list.premier();
        while (iterateur != null) {
            // double initVitesse = iterateur.vitesse;
            if (projectile.checkInRange(iterateur) && iterateur.HP > 0/* && !projectile.getTirePas() */) {

                /*
                 * ShotSound s = new ShotSound("../sons/sorcier.wav");
                 * s.shotSound();
                 */
                gq.setColor(Color.WHITE);
                gq.drawLine(projectile.x + 32, projectile.y + 32, iterateur.getX(), iterateur.getY());
                // System.out.println(iterateur.vitesse);
                iterateur.slowerMonster(1);
            } else if (!projectile.checkInRange(iterateur) && iterateur.HP > 0) {
                iterateur.resetMonsterSpeed();

            }

            iterateur = list.suivant(iterateur);
        }
    }
    // projectile.setTirePas(true);

}
