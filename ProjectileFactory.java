public class ProjectileFactory {
    public static Projectile createProjectile(ProjectileType type,int x, int y) {
        return switch (type) {
            case CANON -> new Canon(60, 15, 120, x, y, 150);
            case TOUR -> new Tours1(30, 10, 60, x, y, 100);
            case TOUR_SORCIER -> new TourSorcier(90, 180, 1, x, y, 200);
            default -> throw new IllegalArgumentException("Type de projectile non utilisable : " + type);
        };
    }
}
