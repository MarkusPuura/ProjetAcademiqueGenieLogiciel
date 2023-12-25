import java.util.ArrayList;

public class TourController {
    private ArrayList<Tours1> towersList;
    private final GamePanel gamePanel;


    public  TourController(GamePanel gamePanel){
        towersList = new ArrayList<>();
        this.gamePanel = gamePanel;

    }

    public void addTower(Tours1 tower) {
        if (checkValidPlacementTour(tower.getX(), tower.getY())) {
            towersList.add(tower);
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

        for (Tours1 tour : towersList) {
            int tourX = tour.getX();
            int tourY = tour.getY();

            if (x < tourX + towerSize && x + towerSize > tourX &&
                    y < tourY + towerSize && y + towerSize > tourY) {
                return false;
            }
        }


        return true;
    }
    public ArrayList<Tours1> getTowersList() {
        return towersList;
    }

    public void setTowersList(ArrayList<Tours1> towersList) {
        this.towersList = towersList;
    }
    public void afficheListeTours() {
        System.out.println("Tour List:");
        for (Tours1 tower : towersList) {
            System.out.println(tower);
        }
    }
}
