import java.util.ArrayList;

public class TourController {
    private ArrayList<Projectile> towersList;
    private final GamePanel gamePanel;
    private final int largeurProjectile;



    public  TourController(GamePanel gamePanel, int largeurProjectile){
        towersList = new ArrayList<>();
        this.gamePanel = gamePanel;
        this.largeurProjectile = largeurProjectile;

    }

    public void addTower(ProjectileType type, int x, int y) {
        if (checkValidPlacementTour(x,y) ){
            Projectile projectile = ProjectileFactory.createProjectile(type,x,y);

            towersList.add(projectile);
            gamePanel.kama.buyProjectile(gamePanel.tours1inventaire.getPrice());

        } else {
            gamePanel.draggedTourImage = null;
        }
    }
    public  boolean checkValidPlacementTour(int x, int y) {
        int towerSize = 2*gamePanel.TailleCarre;


        if (x < 0 || y < 0 || x + towerSize > gamePanel.LargeurEcran || y + towerSize > gamePanel.HauteurEcran) {
            return false;
        }

        for (Projectile tour : towersList) {
            int tourX = tour.getX();
            int tourY = tour.getY();

            if (x < tourX + towerSize && x + towerSize > tourX &&
                    y < tourY + towerSize && y + towerSize > tourY) {
                return false;
            }
        }


        return true;
    }
    public ArrayList<Projectile> getTowersList() {
        return towersList;
    }

    public void setTowersList(ArrayList<Projectile> towersList) {
        this.towersList = towersList;
    }
    public void afficheListeTours() {
        System.out.println("Tour List:");
        for (Projectile tower : towersList) {
            System.out.println(tower);
        }
    }
    public Projectile selectProjectileToAmeliorate(int mouseX, int mouseY) {
        for (Projectile tower : towersList) {

            if (mouseX >= tower.getX() && mouseX <= tower.getX() + largeurProjectile&&
                    mouseY >= tower.getY() && mouseY <= tower.getY() + largeurProjectile ) {

                return tower;
            }
        }
        return null;
    }

}
