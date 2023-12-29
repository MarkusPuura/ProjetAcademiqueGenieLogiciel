import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class MouseController {
    private final GamePanel gamePanel;
    private final PathController pathController;
    final int TailleOriginalPersonnages = 16; // personnages + objets de taille 16*16
    final int echelle = 2;
    final int TailleCarre = TailleOriginalPersonnages * echelle;
    private boolean isOnPathBoolean = false;

    public MouseController(GamePanel gamePanel,PathController pathController) {
        this.gamePanel = gamePanel;
        this.pathController = pathController;

    }

    public void createProjectile(int x, int y) {
        //if (!pathController.isOnPath(x, y)) {
       // Tours1 tourProjectile =  new Tours1(30, 10, 1, x, y,100);
        switch (gamePanel.toursSelected.getNumType()){
            case 1 :
                gamePanel.tourController.addTower(ProjectileType.TOUR,x,y);
                break;
            case 2 :
                gamePanel.tourController.addTower(ProjectileType.CANON,x,y);
                break;
            case 3 :
                gamePanel.tourController.addTower(ProjectileType.TOUR_SORCIER,x,y);
            default :
                break;

        }
       // gamePanel.kama.buyProjectile(gamePanel.tours1inventaire.getPrice());

       // tourProjectile.afficherTours1();

    }

    private int[] placementCarreCoorrect(int mouseX, int mouseY) {
        int[] coordinates = new int[2];
        int x, y;
        if ((mouseX) % TailleCarre < TailleCarre / 2) {
            x = mouseX - (mouseX) % TailleCarre - TailleCarre;
        } else {
            x = mouseX - (mouseX) % TailleCarre;
        }
        if ((mouseY) % TailleCarre < TailleCarre / 2) {
            y = mouseY - (mouseY) % TailleCarre - TailleCarre;
        } else {
            y = mouseY - (mouseY) % TailleCarre;
        }
        coordinates[0] = x;
        coordinates[1] = y;
        return coordinates;
    }

    public void initializeMouseListener() {
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x, y;
                if ((e.getX())%TailleCarre < TailleCarre/2){
                    x = e.getX() - (e.getX())%TailleCarre - TailleCarre;
                } else {
                    x = e.getX() - (e.getX())%TailleCarre;
                }
                if ((e.getY())%TailleCarre < TailleCarre/2){
                    y = e.getY() - (e.getY())%TailleCarre - TailleCarre;
                } else {
                    y = e.getY() - (e.getY())%TailleCarre;
                }
                handleMousePress(x, y);
                gamePanel.setIsDragging(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                handleMouseRelease(e.getX(), e.getY());
                gamePanel.setIsDragging(false);
            }

        });

        gamePanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x, y;
                if ((e.getX())%TailleCarre < TailleCarre/2){
                    x = e.getX() - (e.getX())%TailleCarre - TailleCarre;
                } else {
                    x = e.getX() - (e.getX())%TailleCarre;
                }
                if ((e.getY())%TailleCarre < TailleCarre/2){
                    y = e.getY() - (e.getY())%TailleCarre - TailleCarre;
                } else {
                    y = e.getY() - (e.getY())%TailleCarre;
                }
                handleMouseDrag(x, y);
            }

        });

    }

    private void handleMousePress(int mouseX, int mouseY){
        System.out.println(gamePanel.fin);
        //boolean affordAndClick = gamePanel.barreInventaire.selectProjectileFromInventory(mouseX,mouseY);
        gamePanel.toursSelected = gamePanel.barreInventaire.selectProjectileFromInventory(mouseX,mouseY, gamePanel.kama);
        System.out.println(gamePanel.toursSelected);
         //boolean affordAndClick = gamePanel.tours1inventaire.canAffordAndClickTours1(mouseX, mouseY, gamePanel.TailleCarre, gamePanel.HauteurEcran, gamePanel.kama);
       /* System.out.println(affordAndClick);
        System.out.println(gamePanel);
        if(affordAndClick && gamePanel.fin !=2){
          //  System.out.println("press");
            gamePanel.toursSelected = gamePanel.tours1inventaire.getSelectedTower(mouseX, mouseY, gamePanel.TailleCarre, gamePanel.HauteurEcran, gamePanel.kama);
            //System.out.println(toursSelected);
            gamePanel.toursSelected.afficherProjectile();
            //gamePanel.nbClics++;

        }
        else{
            gamePanel.toursSelected = null;


        }*/
    }
    private void handleMouseRelease(int mouseX, int mouseY){
        //System.out.println("release");

        if(!pathController.isOnPath(mouseX,mouseY) && gamePanel.toursSelected != null&& gamePanel.fin !=2&&gamePanel.fin!=1){
            isOnPathBoolean = false;
            int x, y;
            if ((mouseX)%TailleCarre < TailleCarre/2){
                x = mouseX - mouseX%TailleCarre - TailleCarre;
            } else {
                x = mouseX - mouseX%TailleCarre;
            }
            if (mouseY%TailleCarre < TailleCarre/2){
                y = mouseY - mouseY%TailleCarre - TailleCarre;
            } else {
                y = mouseY - mouseY%TailleCarre;
            }
            createProjectile(x,y);
            gamePanel.tourController.afficheListeTours();
            //gamePanel.kama.buyProjectile(gamePanel.tours1inventaire.getPrice());

        }
        else if (pathController.isOnPath(mouseX, mouseY)&& gamePanel.fin !=2&&gamePanel.fin!=1) {
            isOnPathBoolean= true;
            //System.out.println("on Path");
            gamePanel.draggedTourImage = null;

        }
        gamePanel.toursSelected = null;
        gamePanel.repaint();
    }
    private void handleMouseDrag(int mouseX, int mouseY){
        if (gamePanel.toursSelected != null&& gamePanel.fin !=2&&gamePanel.fin!=1) {
            gamePanel.setMousePosition(mouseX,mouseY);
            gamePanel.drawTourImageAtPosition(gamePanel.toursSelected.image, gamePanel.getMouseX(), gamePanel.getMouseY());

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


    public boolean isOnPathBoolean() {
        return isOnPathBoolean;
    }

    public void setOnPathBoolean(boolean onPathBoolean) {
        isOnPathBoolean = onPathBoolean;
    }
}
