package Vue;

import java.awt.*;
import java.io.IOException;

import Controleur.ListeMonstresVivants;
import Modele.*;
import Modele.proj.ShootStrategy;

public class DirectShoot implements ShootStrategy {

    @Override
    public void tirer(Graphics2D gq, Projectile projectile, Monstres target, ListeMonstresVivants list, int distance) {

        if (target != null && projectile.checkInRange(target) && target.HP > 0 && !projectile.getTirePas()) {
            ShotSound.shotSound("../sons/tour2.wav");
            Stroke oldStroke = gq.getStroke();
            float strokeWidth = 9.0f;
            gq.setStroke(new BasicStroke(strokeWidth));
            gq.setColor(Color.RED);

            int startX = projectile.x + 32;
            int startY = projectile.y + 32;
            int endX = target.getX();
            int endY = target.getY();
            gq.drawLine(startX, startY, endX, endY);
            gq.setColor(Color.YELLOW);
            int explosionRadius = 20;

            gq.fillOval(endX - explosionRadius, endY - explosionRadius, 2 *
                    explosionRadius, 2 * explosionRadius);

            target.looseLife(projectile.getDamage());
            projectile.setTirePas(true);
            gq.setStroke(oldStroke);

        }
    }
}
