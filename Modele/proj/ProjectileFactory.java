package Modele.proj;

import Modele.Projectile;
import Modele.differentsTours.Canon;
import Modele.differentsTours.TourSorcier;
import Modele.differentsTours.Tours1;

public class ProjectileFactory {
    public static Projectile createProjectile(ProjectileType type, int x, int y) {
        return switch (type) {
            case CANON -> new Canon(60, 15, 90, x, y, 150);
            case TOUR -> new Tours1(30, 10, 30, x, y, 100);
            case TOUR_SORCIER -> new TourSorcier(90, 5, 180, x, y, 200);
            default -> throw new IllegalArgumentException("Type de projectile non utilisable : " + type);
        };
    }
}
