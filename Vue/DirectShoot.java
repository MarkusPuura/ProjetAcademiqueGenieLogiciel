package Vue;

import java.awt.*;

import Controleur.ListeMonstresVivants;
import Modele.*;
import Modele.proj.ShootStrategy;

public class DirectShoot implements ShootStrategy {

    @Override
    public void tirer(Graphics2D gq, Projectile projectile, Monstres target, ListeMonstresVivants list, int distance) {
        if (target != null && projectile.checkInRange(target) && target.HP > 0 && !projectile.getTirePas()) {

            gq.setColor(Color.WHITE);
            gq.drawLine(projectile.x + 32, projectile.y + 32, target.getX(), target.getY());
            target.looseLife(projectile.getDamage());
            projectile.setTirePas(true);
        }
    }
}
