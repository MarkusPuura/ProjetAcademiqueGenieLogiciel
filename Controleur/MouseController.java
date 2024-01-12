package Controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Modele.Projectile;
import Modele.proj.ProjectileType;
import Vue.GamePanel;

public class MouseController {
    int lvl;
    private final GamePanel gamePanel;
    private final PathController pathController;
    final int TailleOriginalPersonnages = 16; // personnages + objets de taille 16*16
    final int echelle = 2;
    final int TailleCarre = TailleOriginalPersonnages * echelle;
    private boolean isOnPathBoolean = false;

    public MouseController(GamePanel gamePanel, PathController pathController, int lvl) {
        this.gamePanel = gamePanel;
        this.pathController = pathController;
        this.lvl = lvl;

    }

    public void createProjectile(int x, int y) {
        // if (!pathController.isOnPath(x, y)) {
        // Tours1 tourProjectile = new Tours1(30, 10, 1, x, y,100);
        switch (gamePanel.toursSelected.getNumType()) {
            case 1:
                gamePanel.tourController.addTower(ProjectileType.TOUR, x, y, this.lvl);
                break;
            case 2:
                gamePanel.tourController.addTower(ProjectileType.CANON, x, y, this.lvl);
                break;
            case 3:
                gamePanel.tourController.addTower(ProjectileType.TOUR_SORCIER, x, y, this.lvl);
            default:
                break;

        }

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
            public void mouseClicked(MouseEvent e) {
                if (gamePanel.fin != 1 && gamePanel.fin != 2 && !gamePanel.isPaused) {
                    if (!gamePanel.tourController.isDisplayButtons()) {
                        // System.out.println("!bouton");
                        handleMouseClick(e.getX(), e.getY());
                    } else if (gamePanel.tourController.isDisplayButtons()) {
                        // System.out.println("bouton");
                        handleMouseClickButtons(e.getX(), e.getY());
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (gamePanel.fin != 1 && gamePanel.fin != 2 && !gamePanel.isPaused) {
                    int x, y;
                    if ((e.getX()) % TailleCarre < TailleCarre / 2) {
                        x = e.getX() - (e.getX()) % TailleCarre - TailleCarre;
                    } else {
                        x = e.getX() - (e.getX()) % TailleCarre;
                    }
                    if ((e.getY()) % TailleCarre < TailleCarre / 2) {
                        y = e.getY() - (e.getY()) % TailleCarre - TailleCarre;
                    } else {
                        y = e.getY() - (e.getY()) % TailleCarre;
                    }
                    handleMousePress(x, y);
                    gamePanel.setIsDragging(true);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (gamePanel.fin != 1 && gamePanel.fin != 2 && !gamePanel.isPaused) {
                    handleMouseRelease(e.getX(), e.getY());
                    gamePanel.setIsDragging(false);
                }
            }

        });

        gamePanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (gamePanel.fin != 1 && gamePanel.fin != 2 && !gamePanel.isPaused
                        && gamePanel.toursSelected != null) {
                    int x, y;
                    if ((e.getX()) % TailleCarre < TailleCarre / 2) {
                        x = e.getX() - (e.getX()) % TailleCarre - TailleCarre;
                    } else {
                        x = e.getX() - (e.getX()) % TailleCarre;
                    }
                    if ((e.getY()) % TailleCarre < TailleCarre / 2) {
                        y = e.getY() - (e.getY()) % TailleCarre - TailleCarre;
                    } else {
                        y = e.getY() - (e.getY()) % TailleCarre;
                    }
                    handleMouseDrag(x, y);
                }
            }

        });

    }

    private void handleMousePress(int mouseX, int mouseY) {
        // if (gamePanel.fin != 1 && gamePanel.fin != 2 && !gamePanel.isPaused) {
        // System.out.println(gamePanel.fin);
        gamePanel.toursSelected = gamePanel.barreInventaire.selectProjectileFromInventory(mouseX, mouseY,
                gamePanel.or);
        // }
    }

    private void handleMouseRelease(int mouseX, int mouseY) {
        // System.out.println("release");

        if (!pathController.isOnPath(mouseX, mouseY, lvl) && gamePanel.toursSelected != null) {
            isOnPathBoolean = false;
            int x, y;
            if ((mouseX) % TailleCarre < TailleCarre / 2) {
                x = mouseX - mouseX % TailleCarre - TailleCarre;
            } else {
                x = mouseX - mouseX % TailleCarre;
            }
            if (mouseY % TailleCarre < TailleCarre / 2) {
                y = mouseY - mouseY % TailleCarre - TailleCarre;
            } else {
                y = mouseY - mouseY % TailleCarre;
            }
            createProjectile(x, y);
            gamePanel.tourController.afficheListeTours();

        } else if (pathController.isOnPath(mouseX, mouseY, lvl) && gamePanel.fin != 2 && gamePanel.fin != 1) {
            isOnPathBoolean = true;
            // System.out.println("on Path");
            gamePanel.draggedTourImage = null;

        }
        gamePanel.toursSelected = null;
        gamePanel.repaint();
    }

    private void handleMouseDrag(int mouseX, int mouseY) {
        gamePanel.setMousePosition(mouseX, mouseY);
        if (this.lvl == 1) {
            gamePanel.drawTourImageAtPosition(gamePanel.toursSelected.image, gamePanel.getMouseX(),
                    gamePanel.getMouseY(), lvl);
        }
        if (this.lvl == 2) {
            gamePanel.drawTourImageAtPosition(gamePanel.toursSelected.image2, gamePanel.getMouseX(),
                    gamePanel.getMouseY(), lvl);
        }

    }

    private void handleMouseClick(int mouseX, int mouseY) {
        // if (gamePanel.fin != 1 && gamePanel.fin != 2 && !gamePanel.isPaused) {
        Projectile clickedTower = gamePanel.tourController.selectProjectileToAmeliorate(mouseX, mouseY);
        if (clickedTower != null) {
            gamePanel.tourController.setLastClickedTower(clickedTower);
            gamePanel.tourController.setDisplayButtons(true);
            gamePanel.tourController.setSelectedTowerX(clickedTower.getX());
            gamePanel.tourController.setSelectedTowerY(clickedTower.getY());
            // System.out.println(gamePanel.tourController.getLastClickedTower());

        }
        // System.out.println(gamePanel.tourController.getLastClickedTower());

        // }
    }

    public void handleMouseClickButtons(int mouseX, int mouseY) {
        int projectileX = gamePanel.tourController.getSelectedTowerX();
        int projectileY = gamePanel.tourController.getSelectedTowerY();
        int buttonWidth = 2 * TailleCarre;
        int buttonHeight = 2 * TailleCarre;

        if (mouseX >= projectileX - 2 * TailleCarre - 5 && mouseX <= projectileX - 2 * TailleCarre - 5 + buttonWidth &&
                mouseY >= projectileY - 2 * TailleCarre && mouseY <= projectileY - 2 * TailleCarre + buttonHeight) {
            System.out.println("amelioration");
            gamePanel.tourController.getLastClickedTower().setAmeliorationValue();
            // gamePanel.tourController.getLastClickedTower().amelioration(1);
            gamePanel.tourController.getLastClickedTower().getModifAttribut()
                    .setAmelioration(gamePanel.tourController.getLastClickedTower());
            // gamePanel.tourController.getLastClickedTower().setAmeliorationValue();
        } else if (mouseX >= projectileX && mouseX <= projectileX + buttonWidth &&
                mouseY >= projectileY - 2 * TailleCarre && mouseY <= projectileY - 2 * TailleCarre + buttonHeight) {
            System.out.println("vente");
            gamePanel.or.portefeuille += gamePanel.tourController.getLastClickedTower().getSellValue();
            gamePanel.tourController.removeTower(gamePanel.tourController.getLastClickedTower());
            gamePanel.tourController.setDisplayButtons(false);
            gamePanel.tourController.setLastClickedTower(null);
            // gamePanel.tourController.getLastClickedTower().setVenteAttribut();
        } else if (mouseX >= projectileX + 2 * TailleCarre + 5
                && mouseX <= projectileX + 2 * TailleCarre + 5 + buttonWidth &&
                mouseY >= projectileY - 2 * TailleCarre && mouseY <= projectileY - 2 * TailleCarre + buttonHeight) {
            System.out.println("retour");
            gamePanel.tourController.setDisplayButtons(false);
            gamePanel.tourController.setLastClickedTower(null);

        } else {
            gamePanel.tourController.setDisplayButtons(false);
            gamePanel.tourController.setLastClickedTower(null);

        }
    }

    private void handleMouseEntered(int mouseX, int mouseY) {
        boolean affordAndClick = gamePanel.tours1inventaire.canAffordAndClickTours1(mouseX, mouseY,
                gamePanel.TailleCarre, gamePanel.HauteurEcran, gamePanel.or);
        // System.out.println(affordAndClick);
        if (affordAndClick) {
            // System.out.println("souris survol");
        }
    }

    private void handleMouseExited(int mouseX, int mouseY) {
        boolean affordAndClick = gamePanel.tours1inventaire.canAffordAndClickTours1(mouseX, mouseY,
                gamePanel.TailleCarre, gamePanel.HauteurEcran, gamePanel.or);
        if (!affordAndClick) {
            // System.out.println("plus souris survol");
        }
    }

    public boolean isOnPathBoolean() {
        return isOnPathBoolean;
    }

    public void setOnPathBoolean(boolean onPathBoolean) {
        isOnPathBoolean = onPathBoolean;
    }
}
