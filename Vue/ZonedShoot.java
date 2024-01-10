package Vue;

import java.awt.*;

import Controleur.ListeMonstresVivants;
import Modele.Monstres;
import Modele.Projectile;
import Modele.proj.ShootStrategy;

public class ZonedShoot implements ShootStrategy {
    @Override
    public void tirer(Graphics2D gq, Projectile projectile, Monstres target, ListeMonstresVivants list, int distance) {
        if (target != null && projectile.checkInRange(target) && target.HP > 0 && !projectile.getTirePas()) {
            System.out.println("actionTemp");
            gq.setColor(Color.WHITE);

            gq.drawRect(target.getX(), target.getY(), distance, distance);

            gq.drawLine(projectile.x + 32, projectile.y + 32, target.getX(), target.getY());
            target.looseLife(projectile.getDamage());
            System.out.println("Monster HP after hit: " + target.HP);
            Monstres iterateur = list.premier();
            while (iterateur != null) {
                if (list.estDansZone(target, iterateur, distance)) {
                    gq.drawRect(iterateur.getX(), iterateur.getY(), distance, distance);
                    gq.drawLine(projectile.x + 32, projectile.y + 32, iterateur.getX(), iterateur.getY());

                    iterateur.looseLife(projectile.getDamage());
                    System.out.println("Other Monster HP after hit: " + iterateur.HP);
                }
                iterateur = list.suivant(iterateur);
            }
            projectile.setTirePas(true);

        }

    }
}
