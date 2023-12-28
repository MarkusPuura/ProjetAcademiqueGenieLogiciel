import java.util.ArrayList;
import java.util.List;

public class BarreInventaire {



        private final int largeurInventaire;
        private final int largeurProjectile;
        private final int espacementEntreProjectiles;
        private final List<Projectile> projectiles;

        public BarreInventaire(int largeurInventaire, int largeurProjectile, int espacementEntreProjectiles) {
            this.largeurInventaire = largeurInventaire;
            this.largeurProjectile = largeurProjectile;
            this.espacementEntreProjectiles = espacementEntreProjectiles;
            this.projectiles = new ArrayList<>();
        }

        public void addProjectile(Projectile projectile) {
            projectiles.add(projectile);
        }

        public void organisationProjectiles(int HauteurEcran, int TailleCarre) {
            int totalProjectilesWidth = (largeurProjectile + espacementEntreProjectiles) * projectiles.size() - espacementEntreProjectiles;

            int startX = (largeurInventaire - totalProjectilesWidth) / 2;
            int y = HauteurEcran - 3*TailleCarre;
            for (Projectile projectile : projectiles) {
                projectile.setX(startX);
                projectile.setY(y);
                startX += largeurProjectile + 2*espacementEntreProjectiles;
            }
        }
        public Projectile selectProjectileFromInventory(int mouseX, int mouseY,Kama k) {
            //System.out.println("fun");
            for (Projectile projectile : projectiles) {
                //System.out.println(projectile);
              //  System.out.println(mouseX+ " "+mouseY);

                if (mouseX >= projectile.getX()- largeurProjectile/2 && mouseX <= projectile.getX() + largeurProjectile/2 &&
                        mouseY >= projectile.getY() - largeurProjectile/2&& mouseY <= projectile.getY() + largeurProjectile/2 && projectile.hasEnoughMoney(k)) {
                    //System.out.println("if");

                    return projectile;
                }
            }
            return null;
        }

    public int getLargeurInventaire() {
        return this.largeurInventaire;
    }

    public int getLargeurProjectile() {
        return largeurProjectile;
    }

    public int getEspacementEntreProjectiles() {
        return espacementEntreProjectiles;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }
    public void afficheListeListeBarreProjectile() {
        System.out.println("Tour List:");
        for (Projectile tower : projectiles) {
            System.out.println(tower);
        }
    }



}


