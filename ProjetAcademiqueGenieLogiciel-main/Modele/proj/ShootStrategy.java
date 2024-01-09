package Modele.proj;
import java.awt.*;

import Modele.Monstres;
import Modele.Projectile;

public interface ShootStrategy {

    void tirer(Graphics2D gq, Projectile projectile, Monstres target);

}
