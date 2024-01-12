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
            Stroke oldStroke = gq.getStroke();
            float strokeWidth = 12.0f;
            gq.setStroke(new BasicStroke(strokeWidth));
            int startX = projectile.x + 32;
            int startY = projectile.y + 32;
            int endX = target.getX();
            int endY = target.getY();
            gq.setColor(Color.orange);

            gq.drawRect(endX, endY, distance, distance);

            gq.drawLine(startX, startY, endX, endY);
            gq.setColor(Color.BLACK);
            int explosionRadius = 20;

            gq.fillOval(endX - explosionRadius, endY - explosionRadius, 2 *
                    explosionRadius, 2 * explosionRadius);
            target.looseLife(projectile.getDamage());
            Monstres iterateur = list.premier();
            while (iterateur != null) {
                if (list.estDansZone(target, iterateur, distance)) {
                    gq.drawRect(iterateur.getX(), iterateur.getY(), distance, distance);
                    // gq.drawLine(projectile.x + 32, projectile.y + 32, iterateur.getX(),
                    // iterateur.getY());

                    iterateur.looseLife(projectile.getDamage());
                }
                iterateur = list.suivant(iterateur);
            }
            projectile.setTirePas(true);
            gq.setStroke(oldStroke);

        }

    }
}
