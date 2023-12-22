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
            Tours1 tourProjectile =  new Tours1(30, 3, 1, x, y,100);
            gamePanel.tourController.addTower(tourProjectile);
            tourProjectile.afficherTours1();

        }
    }

    public void initializeMouseListener() {
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePress(e.getX(), e.getY());
                gamePanel.setIsDragging(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleMouseRelease(e.getX(), e.getY());
                gamePanel.setIsDragging(false);
            }
            /*@Override
            public void mouseEntered(MouseEvent e) {

                handleMouseEntered(e.getX(), e.getY());
            }*/

            /*@Override
            public void mouseExited(MouseEvent e) {
                handleMouseExited(e.getX(), e.getY());
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                //handleMouseClick(e.getX(), e.getY());
                boolean affordAndClick = gamePanel.tours1inventaire.canAffordAndClickTours1(e.getX(), e.getY(), gamePanel.TailleCarre, gamePanel.HauteurEcran, gamePanel.kama);

                if(affordAndClick){
                    System.out.println("clicked");
                }


            }*/
        });

        gamePanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseDrag(e.getX(), e.getY());
            }
           /* @Override
            public void mouseMoved(MouseEvent e) {
                gamePanel.setMousePosition(e.getX(), e.getY());
            }*/
        });

    }

    private void handleMousePress(int mouseX, int mouseY){

        boolean affordAndClick = gamePanel.tours1inventaire.canAffordAndClickTours1(mouseX, mouseY, gamePanel.TailleCarre, gamePanel.HauteurEcran, gamePanel.kama);
        System.out.println(affordAndClick);
        if(affordAndClick){
            System.out.println("press");
            gamePanel.toursSelected = gamePanel.tours1inventaire.getSelectedTower(mouseX, mouseY, gamePanel.TailleCarre, gamePanel.HauteurEcran, gamePanel.kama);
            //System.out.println(toursSelected);
            gamePanel.toursSelected.afficherTours1();
            //gamePanel.nbClics++;

        }
        else{
            gamePanel.toursSelected = null;


        }
    }
    private void handleMouseRelease(int mouseX, int mouseY){
        System.out.println("release");
        if(!pathController.isOnPath(mouseX,mouseY) && gamePanel.toursSelected != null){
            createProjectile(mouseX,mouseY);
            gamePanel.kama.buyProjectile(gamePanel.tours1inventaire.getPrice());

        }
        else if (pathController.isOnPath(mouseX, mouseY)) {
            System.out.println("on Path");

        }
        gamePanel.toursSelected = null;
        gamePanel.repaint();
    }
    private void handleMouseDrag(int mouseX, int mouseY){
        if (gamePanel.toursSelected != null) {
            gamePanel.setMousePosition(mouseX,mouseY);

            gamePanel.drawTourImageAtPosition(gamePanel.toursSelected.getImageDragMouse(), gamePanel.getMouseX(), gamePanel.getMouseY());
        }
        else {
            System.out.println("selected null pour draged");


        }

    }

    private void handleMouseEntered(int mouseX, int mouseY) {
        boolean affordAndClick = gamePanel.tours1inventaire.canAffordAndClickTours1(mouseX, mouseY, gamePanel.TailleCarre, gamePanel.HauteurEcran, gamePanel.kama);
        System.out.println(affordAndClick);
        if(affordAndClick){
            System.out.println("souris survol");
        }
    }
    private void handleMouseExited(int mouseX, int mouseY){
        boolean affordAndClick = gamePanel.tours1inventaire.canAffordAndClickTours1(mouseX, mouseY, gamePanel.TailleCarre, gamePanel.HauteurEcran, gamePanel.kama);
        if(!affordAndClick){
            System.out.println("plus souris survol");
        }
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
