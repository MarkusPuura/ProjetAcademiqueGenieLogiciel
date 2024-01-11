package Controleur;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Modele.Projectile;
import Modele.proj.ButtonsProjectile;
import Modele.proj.ProjectileFactory;
import Modele.proj.ProjectileType;
import Vue.GamePanel;

public class TourController {
    private ArrayList<Projectile> towersList;
    private final GamePanel gamePanel;
    private final int largeurProjectile;
    private Projectile lastClickedTower;
    private boolean displayButtons;
    private int selectedTowerX;
    private int selectedTowerY;
    private final ButtonsProjectile buttonsProjectile;

    public TourController(GamePanel gamePanel, int largeurProjectile) {
        towersList = new ArrayList<>();
        this.gamePanel = gamePanel;
        this.largeurProjectile = largeurProjectile;
        this.lastClickedTower = null;
        this.buttonsProjectile = new ButtonsProjectile();

    }

    public BufferedImage getAmelioratePic() {
        return buttonsProjectile.getAmeliorateB();
    }

    public BufferedImage getSellPic() {
        return buttonsProjectile.getSellB();
    }

    public BufferedImage getReturnPic() {
        return buttonsProjectile.getReturnB();
    }

    public Projectile getLastClickedTower() {
        return lastClickedTower;
    }

    public void setLastClickedTower(Projectile lastClickedTower) {
        this.lastClickedTower = lastClickedTower;
    }

    public void addTower(ProjectileType type, int x, int y, int lvl) {
        if (checkValidPlacementTour(x, y)) {
            Projectile projectile = ProjectileFactory.createProjectile(type, x, y);

            towersList.add(projectile);
            gamePanel.or.buyProjectile(gamePanel.tours1inventaire.getPrice());

        } else {
            gamePanel.draggedTourImage = null;
        }
    }

    public void removeTower(Projectile p) {
        towersList.remove(p);
    }

    public boolean checkValidPlacementTour(int x, int y) {
        int towerSize = 2 * gamePanel.TailleCarre;

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

            if (mouseX >= tower.getX() && mouseX <= tower.getX() + largeurProjectile &&
                    mouseY >= tower.getY() && mouseY <= tower.getY() + largeurProjectile) {

                return tower;
            }
        }
        return null;
    }

    public boolean isDisplayButtons() {
        return displayButtons;
    }

    public void setDisplayButtons(boolean displayButtons) {
        this.displayButtons = displayButtons;
    }

    public int getSelectedTowerX() {
        return selectedTowerX;
    }

    public void setSelectedTowerX(int selectedTowerX) {
        this.selectedTowerX = selectedTowerX;
    }

    public int getSelectedTowerY() {
        return selectedTowerY;
    }

    public void setSelectedTowerY(int selectedTowerY) {
        this.selectedTowerY = selectedTowerY;
    }

}
