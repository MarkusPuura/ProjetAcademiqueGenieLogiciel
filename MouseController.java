import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class MouseController {
    private final GamePanel gamePanel;
    private final PathController pathController;

    public MouseController(GamePanel gamePanel,PathController pathController) {
        this.gamePanel = gamePanel;
        this.pathController = pathController;

    }

    public void createProjectile(int x, int y) {
        if (!pathController.isOnPath(x, y)) {
            Tours1 tourProjectile =  new Tours1(30, 3, 1, x, y,6);
            gamePanel.tourController.addTower(tourProjectile);
            tourProjectile.afficherTours1();

        }
    }

    public void initializeMouseListener() {
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e.getX(), e.getY());
            }
        });
    }

    private void handleMouseClick(int mouseX, int mouseY) {

        boolean isPointOnPath = pathController.isOnPath(mouseX, mouseY);
        System.out.println("Le point se trouve sur le chemin : " + isPointOnPath);
        boolean affordAndClick = gamePanel.tours1inventaire.canAffordAndClickTours1(mouseX, mouseY, gamePanel.TailleCarre, gamePanel.HauteurEcran, gamePanel.kama);
        System.out.println("affordAndClick : " + affordAndClick);
        if (gamePanel.nbClics == 1 &  !pathController.isOnPath(mouseX,mouseY)) {

            System.out.println("nbclics == 1 & tours != null");
            System.out.println(gamePanel.toursSelected);

            createProjectile(mouseX,mouseY);
            gamePanel.nbClics = 0;
            gamePanel.toursSelected = null;
            System.out.println(gamePanel.toursSelected);

        }
        else {
            if (affordAndClick) {

                gamePanel.toursSelected = gamePanel.tours1inventaire.getSelectedTower(mouseX, mouseY, gamePanel.TailleCarre, gamePanel.HauteurEcran, gamePanel.kama);
                //System.out.println(toursSelected);
                gamePanel.toursSelected.afficherTours1();
                gamePanel.nbClics++;

                gamePanel.kama.buyProjectile(gamePanel.tours1inventaire.getPrice());
            }

        }



    }
}
