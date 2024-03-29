package Vue;

import java.awt.*;
import javax.swing.*;

import Controleur.*;
import Modele.*;
import Modele.differentsTours.Canon;
import Modele.differentsTours.TourSorcier;
import Modele.differentsTours.Tours1;

import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    public boolean isPaused = false;

    final int TailleOriginalPersonnages = 16; // personnages + objets de taille 16*16
    final int echelle = 2;
    public final int TailleCarre = TailleOriginalPersonnages * echelle;

    final int ColonnesEcran = 35;
    final int LignesEcran = 20;
    public final int LargeurEcran = ColonnesEcran * TailleCarre; // 35 * (2*16) =
    public final int HauteurEcran = LignesEcran * TailleCarre; // 20 * (2*16) =

    int lvl;
    int niveau = 1; // difficulté du jeu
    int FPS = 60;
    Chrono chrono = new Chrono();
    public Or or = new Or();
    public int fin; // quand passe à 1: le joueur a gagné, si passe à 2: il a perdu.
    public boolean tirePas = false;
    public int counterTirePas = 0;
    public int speedOfTire = 60;
    Tunel tunel;
    Thread gameThread;
    public final BarreInventaire barreInventaire;
    public Tours1 tours1inventaire = new Tours1(30, 10, 60, 6 * TailleCarre, HauteurEcran - 3 * TailleCarre, 100);
    Canon canonInventaire = new Canon(60, 15, 120, 11 * TailleCarre, HauteurEcran - 3 * TailleCarre, 150);
    TourSorcier tourSorcierInventaire = new TourSorcier(90, 5, 180, 15 * TailleCarre, HauteurEcran - 3 * TailleCarre,
            200);

    public TourController tourController;
    Projectile tourToAmeliorate = null;
    public Projectile toursSelected = null;
    int nbClics = 0;
    private final MouseController mouseController;
    private final KeyboardController keaboard;
    PathController pathController = new PathController(TailleCarre, LargeurEcran, HauteurEcran);

    public BufferedImage draggedTourImage;
    private int mouseX;
    private int mouseY;
    boolean isDragging;

    public int ralentir = 0;
    public int est_ralenti = 0;
    public Musique musique;

    public GamePanel(int lvl) {
        this.setPreferredSize(new Dimension(LargeurEcran, HauteurEcran));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true); // ameliorer affichage
        createTunel(); // Création du tunnel à la fin du chemin
        tourController = new TourController(this, 2 * TailleCarre);
        mouseController = new MouseController(this, pathController, lvl);
        mouseController.initializeMouseListener();
        keaboard = new KeyboardController(this);
        keaboard.initializeKeyboardListener();

        barreInventaire = new BarreInventaire(LargeurEcran, 2 * TailleCarre, TailleCarre);
        isDragging = false;
        initializeBarreInventaireList();
        this.lvl = lvl;
        musique = new Musique();

    }

    public void setMousePosition(int x, int y) {
        mouseX = x;
        mouseY = y;
    }

    public void setIsDragging(boolean dragging) {
        isDragging = dragging;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    private void createTunel() {
        tunel = Tunel.getInstance(3 * TailleCarre, 6 * TailleCarre, 30 * TailleCarre, 9 * TailleCarre);
    }

    public void initializeBarreInventaireList() {
        barreInventaire.addProjectile(tours1inventaire);
        barreInventaire.addProjectile(canonInventaire);
        barreInventaire.addProjectile(tourSorcierInventaire);
        barreInventaire.afficheListeListeBarreProjectile();
        barreInventaire.organisationProjectiles(HauteurEcran, TailleCarre);
        barreInventaire.afficheListeListeBarreProjectile();

    }

    public void drawProjectileInInventory(Graphics2D gq) {
        for (Projectile projectile : barreInventaire.getProjectiles()) {
            if (this.lvl == 1) {
                gq.drawImage(projectile.image, projectile.x, projectile.y, TailleCarre * 2, TailleCarre * 2, null);
            }
            if (this.lvl == 2) {
                gq.drawImage(projectile.image2, projectile.x, projectile.y, TailleCarre * 2, TailleCarre * 2, null);
            }
            gq.setColor(Color.WHITE);
            gq.setFont(new Font("Arial", Font.PLAIN, 15));
            gq.drawString(String.valueOf(projectile.getPrice()), projectile.x, HauteurEcran - TailleCarre / 2);
            gq.drawImage(or.image, projectile.x + TailleCarre, HauteurEcran - TailleCarre, TailleCarre - 6,
                    TailleCarre - 6, null);

        }
    }

    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double tempsUpdate = 1000000000 / FPS;
        double ProchainImage = System.nanoTime() + tempsUpdate;

        while (gameThread != null && fin == 0) {
            if (!isPaused) {
                update();
                repaint();
            }

            try { // pour determiner le nbr d'images par seconde
                double tempsRestant = ProchainImage - System.nanoTime();
                tempsRestant = tempsRestant / 1000000; // en millisecondes
                if (tempsRestant < 0) {
                    tempsRestant = 0;
                }
                Thread.sleep((long) tempsRestant);
                ProchainImage += tempsUpdate;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        update();
    }

    public void increaseGameSpeed() {
        FPS *= 2;
    }

    public void decreaseGameSpeed() {
        FPS /= 2;
    }

    GenerationRandomMonstres random = new GenerationRandomMonstres(niveau);
    ListeMonstresVivants liste_monstres = new ListeMonstresVivants();
    // Monstres zombie = new Zombie(20, 4, 5, 0, TailleCarre*2);
    // liste_monstres.ajouterEnFin(zombie);

    int debut = 0;

    public void update() {

        // cree monstres via la GenerationRandomMonstres
        random.generermonstre(chrono, TailleCarre, liste_monstres, debut, this.lvl); // ITERATOR (Design pattern)
        debut = 1; // si pas de monstre ça ne marche pas bizzarement...
        Monstres iterateur = null;
        if (!liste_monstres.isEmpty()) {
            iterateur = liste_monstres.premier();
            est_ralenti = iterateur.est_ralenti;
        }

        if (iterateur != null) {
            iterateur.updateMonstre(TailleCarre, HauteurEcran, LargeurEcran, lvl, ralentir, est_ralenti);
            if (ralentir == 1) {
                ralentir = 0;
            } else {
                ralentir = 1;
            }
            while (liste_monstres.suivant((iterateur)) != null) {
                iterateur = liste_monstres.suivant(iterateur);
                est_ralenti = iterateur.est_ralenti;
                iterateur.updateMonstre(TailleCarre, HauteurEcran, LargeurEcran, lvl, ralentir, est_ralenti);
            }
        }

        // supprime les monstres qui n'ont plus d'HP et ceux qui sont arrivés à la fin
        Monstres suivant;
        iterateur = liste_monstres.premier();
        int suppr = 0;
        if (iterateur != null) {
            if (iterateur.HP <= 0) {
                or.portefeuille += iterateur.or; // on gagne le or pour avoir tué le monstre
                if (iterateur.bebes == 1) {
                    random.genererbebe(TailleCarre, liste_monstres, iterateur.x, iterateur.y, this.lvl);
                }
                liste_monstres.supprimer(iterateur);
                suppr = 1;
            }
            if (iterateur.fin == 1) { // si il est arrivé au chateau
                liste_monstres.supprimer(iterateur);
                suppr = 1;
                tunel.vies--;
            }
        }
        iterateur = liste_monstres.premier();
        if (iterateur != null) {
            while (liste_monstres.suivant((iterateur)) != null) {
                if (suppr == 0) {
                    iterateur = liste_monstres.suivant(iterateur);
                    if (iterateur.HP <= 0) {
                        or.portefeuille += iterateur.or;
                        if (iterateur.bebes == 1) {
                            random.genererbebe(TailleCarre, liste_monstres, iterateur.x, iterateur.y, this.lvl);
                        }
                        suivant = liste_monstres.suivant(iterateur);
                        liste_monstres.supprimer(iterateur);
                        iterateur = suivant;
                        suppr = 1;
                    }
                    try {
                        if (iterateur.fin == 1) { // si il est arrivé au chateau
                            suivant = liste_monstres.suivant(iterateur);
                            liste_monstres.supprimer(iterateur);
                            iterateur = suivant;
                            suppr = 1;
                            tunel.vies--;
                        }
                    } catch (Exception e) {
                        System.out.println("null");
                    }
                } else {
                    suppr = 0;
                    if (iterateur.HP <= 0) {
                        or.portefeuille += iterateur.or;
                        suivant = liste_monstres.suivant(iterateur);
                        liste_monstres.supprimer(iterateur);
                        iterateur = suivant;
                        suppr = 1;
                    }
                }
            }
        }
        // }

        fin = chrono.UpdateChrono(FPS);
        if (tunel.vies == 0) {
            fin = 2; // perdu
        }
        for (Projectile projectile : tourController.getTowersList()) {
            projectile.tempsTireProjectile();
        }
        // tempsTireProjectile();

    }

    public void miseEnPauseDuJeu() {

        isPaused = !isPaused;
        if (isPaused) {
            musique.stopperMusique();
        } else {
            musique.jouerMusiqueEnBoucle("../sons/musique.wav");

        }

    }

    public void drawQuadrillage(Graphics2D gq) {

        Color quadrillage1 = new Color(79, 155, 79);
        Color fond = new Color(107, 175, 107);
        gq.setColor(fond);
        if (this.lvl == 2) {
            Color fond2 = new Color(218, 179, 87);
            gq.setColor(fond2);
        }
        gq.fillRect(0, 0, LargeurEcran, HauteurEcran);

        gq.setColor(quadrillage1);
        if (this.lvl == 2) {
            gq.setColor(Color.black);
        }
        for (int i = 1; i <= ColonnesEcran; i++) {
            gq.fillRect(i * TailleCarre, 0, 1, HauteurEcran);
        }
        for (int i = 1; i <= LignesEcran; i++) {
            gq.fillRect(0, i * TailleCarre, LargeurEcran, 1);
        }
    }

    public void createPath(Graphics2D gq) {

        gq.setColor(Color.black); // la barre en haut et en bas
        gq.fillRect(0, 0, LargeurEcran, TailleCarre);
        gq.fillRect(0, HauteurEcran - 4 * TailleCarre, LargeurEcran, 4 * TailleCarre);

        // chemin
        if (this.lvl == 1) {
            gq.setColor(Color.black);
            gq.fillRect(0, TailleCarre * 2, LargeurEcran - 2 * TailleCarre, 1);
            gq.fillRect(0, TailleCarre * 3, LargeurEcran - 3 * TailleCarre, 1);
            gq.fillRect(LargeurEcran - 2 * TailleCarre, TailleCarre * 2, 2, TailleCarre * 13);
            gq.fillRect(LargeurEcran - 3 * TailleCarre, TailleCarre * 3, 2, TailleCarre * 11);
            gq.fillRect(18 * TailleCarre, 14 * TailleCarre, 14 * TailleCarre, 1);
            gq.fillRect(17 * TailleCarre, 15 * TailleCarre, 16 * TailleCarre, 1);
            gq.fillRect(17 * TailleCarre, 9 * TailleCarre, 2, TailleCarre * 6);
            gq.fillRect(18 * TailleCarre, 8 * TailleCarre, 2, TailleCarre * 6);
            gq.fillRect(6 * TailleCarre, 8 * TailleCarre, 12 * TailleCarre, 1);
            gq.fillRect(6 * TailleCarre, 9 * TailleCarre, 11 * TailleCarre, 1);
            Color chemin = new Color(171, 131, 100);
            gq.setColor(chemin);
            gq.fillRect(0, TailleCarre * 2 + 1, LargeurEcran - 2 * TailleCarre, TailleCarre - 1);
            gq.fillRect(LargeurEcran - 3 * TailleCarre + 2, TailleCarre * 3, TailleCarre - 2, TailleCarre * 11 + 1);
            gq.fillRect(17 * TailleCarre + 2, 14 * TailleCarre + 1, 16 * TailleCarre - 2, TailleCarre - 1);
            gq.fillRect(17 * TailleCarre + 2, 8 * TailleCarre + 1, TailleCarre - 2, TailleCarre * 6);
            gq.fillRect(6 * TailleCarre, 8 * TailleCarre + 1, 12 * TailleCarre - 1, TailleCarre - 1);

            // dessine le chateau
            gq.drawImage(tunel.Imagechateau, tunel.x1, tunel.y1, TailleCarre * 3, TailleCarre * 3, null);
        }
        if (this.lvl == 2) {
            gq.setColor(Color.black);
            gq.fillRect(0, TailleCarre * 2, 16 * TailleCarre, 1);
            gq.fillRect(0, TailleCarre * 3, 15 * TailleCarre, 1);
            gq.fillRect(16 * TailleCarre, TailleCarre * 2, 2, 9 * TailleCarre);
            gq.fillRect(15 * TailleCarre, TailleCarre * 3, 2, 9 * TailleCarre);
            gq.fillRect(16 * TailleCarre, TailleCarre * 11, 14 * TailleCarre, 1);
            gq.fillRect(15 * TailleCarre, TailleCarre * 12, 15 * TailleCarre, 1);

            Color chemin = new Color(171, 131, 100);
            gq.setColor(chemin);
            gq.fillRect(0, TailleCarre * 2 + 1, 16 * TailleCarre, TailleCarre - 1);
            gq.fillRect(15 * TailleCarre + 2, TailleCarre * 3, TailleCarre - 2, TailleCarre * 9);
            gq.fillRect(15 * TailleCarre + 2, TailleCarre * 11 + 1, 15 * TailleCarre, TailleCarre - 1);

            // dessine le chateau
            gq.drawImage(tunel.Imagechateau2, tunel.x2, tunel.y2, TailleCarre * 3, TailleCarre * 3, null);
        }

        // dessine tours dans inventaire
        drawProjectileInInventory(gq);

    }

    public void endOfTheGame(Graphics2D gq) {
        if (fin == 1) { // Si le joueur a gagné (dernier update)
            musique.stopperMusique();
            ShotSound.shotSound("../sons/victoire.wav");

            System.out.println(fin);
            String messageVictoire = "The village is saved :)";
            gq.setColor(Color.red);
            gq.setFont(new Font("Arial", Font.PLAIN, 75));
            gq.drawString(messageVictoire, 5 * TailleCarre, HauteurEcran / 2);

            gameOver();

        }
        if (fin == 2) { // Si le joueur a perdu (dernier update)
            musique.stopperMusique();
            ShotSound.shotSound("../sons/gameOver.wav");

            String messageDefaite = "GAME OVER :(";
            gq.setColor(Color.red);
            gq.setFont(new Font("Arial", Font.PLAIN, 75));
            gq.drawString(messageDefaite, 10 * TailleCarre, HauteurEcran / 2);
        }
    }

    public void gameOver() {
        if (gameOverListener != null) {
            gameOverListener.onGameOver();
        }
    }

    private GameOverListener gameOverListener;

    public void setGameOverListener(GameOverListener listener) {
        this.gameOverListener = listener;
    }

    public interface GameOverListener {
        void onGameOver();
    }

    public void drawInfoKeayboard(Graphics2D gq) {
        String info = "Keyboard  p:paused   q:quit   m:stop music   M:start music";
        gq.setColor(Color.white);
        gq.setFont(new Font("Arial", Font.ITALIC, 20));
        gq.drawString(info, TailleCarre, TailleCarre - 6);
    }

    public void printChrono(Graphics2D gq) {
        String afficherChrono;
        if (chrono.sec < 10) {
            afficherChrono = chrono.min + ":0" + chrono.sec;
        } else {
            afficherChrono = chrono.min + ":" + chrono.sec;
        }
        gq.drawImage(chrono.image, LargeurEcran - 4 * TailleCarre, 0, TailleCarre, TailleCarre, null);
        gq.setColor(Color.WHITE);
        gq.setFont(new Font("Arial", Font.PLAIN, 25));
        gq.drawString(afficherChrono, LargeurEcran - 3 * TailleCarre, TailleCarre - 4);
    }

    public void printRestOfMoney(Graphics2D gq) {
        String afficherOr = ": " + or.portefeuille;
        gq.drawImage(or.image, LargeurEcran - 9 * TailleCarre, 0, TailleCarre, TailleCarre, null);

        gq.drawString(afficherOr, LargeurEcran - 8 * TailleCarre, TailleCarre - 4);
    }

    public void barreChoixProjectiles(Graphics2D gq) {
        // Barre en bas de couleur grise foncée
        Color brun = new Color(97, 72, 54);
        gq.setColor(brun);
        gq.fillRect(0, HauteurEcran - 4 * TailleCarre - TailleCarre / 4, LargeurEcran, TailleCarre / 4);
    }

    public void drawProjectile(Graphics2D gq) {
        for (Projectile projectile : tourController.getTowersList()) {
            if (projectile.isActive()) {
                // projectile.draw(gq);
                if (this.lvl == 1) {
                    gq.drawImage(projectile.image, projectile.x, projectile.y, TailleCarre * 2, TailleCarre * 2, null);
                }
                if (this.lvl == 2) {
                    gq.drawImage(projectile.image2, projectile.x, projectile.y, TailleCarre * 2, TailleCarre * 2, null);
                }

            }
        }
    }

    public void drawLineBetweenMonsterProjectile(Graphics2D gq, Monstres monstre) {
        for (Projectile projectile : tourController.getTowersList()) {
            if (projectile.isActive() && fin != 2 && fin != 1) {
                // projectile.draw(gq);
                projectile.updateTarget(monstre);
                // if (projectile.checkInRange(monstre) && projectile.getTarget() != null &&
                // monstre.HP >0&& fin!=2 && !projectile.getTirePas()) {
                // speedOfTire = projectile.getSpeed();
                // System.out.println(projectile.getTarget());

                projectile.tirer(gq, projectile.getTarget(), liste_monstres, TailleCarre);

                // }
                // else if(fin!=2){
                // System.out.println("projectile.target = null");
                projectile.setTarget(null);
                // System.out.println(projectile.getTarget());
                // }

            }
        }
    }

    public void drawCircleButtonDisposition(Graphics2D gq, int x, int y) {
        int tourCenterX = x - (x % TailleCarre) + TailleCarre;
        int tourCenterY = y - (y % TailleCarre) + TailleCarre;

        int radius = tourController.getLastClickedTower().getRadius();
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f);
        gq.setComposite(alphaComposite);

        gq.setColor(Color.white);
        gq.fillOval(tourCenterX - radius, tourCenterY - radius, 2 * radius, 2 * radius);
    }

    public void drawProjectileDragMouseCircle(Graphics2D gq, int x, int y) {
        if (toursSelected != null) {
            int tourCenterX = x - (x % TailleCarre) + TailleCarre;
            int tourCenterY = y - (y % TailleCarre) + TailleCarre;

            int radius = toursSelected.getRadius();
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f);
            gq.setComposite(alphaComposite);

            if (isDragging) {
                // System.out.println("on path bool : "+ mouseController.isOnPathBoolean());
                if (mouseController.isOnPathBoolean() || !tourController.checkValidPlacementTour(x, y)) {
                    // System.out.println(isDragging + " drawRadius");
                    gq.setColor(Color.red);
                    gq.fillOval(tourCenterX - radius, tourCenterY - radius, 2 * radius, 2 * radius);
                } else {
                    // System.out.println(isDragging + " drawRadius");
                    gq.setColor(Color.blue);
                    gq.fillOval(tourCenterX - radius, tourCenterY - radius, 2 * radius, 2 * radius);
                }
            }
        }
    }

    public void drawTourImageAtPosition(BufferedImage image, int x, int y, int lvl) {
        mouseController.setOnPathBoolean(pathController.isOnPath(x + TailleCarre, y + TailleCarre, lvl));
        if (x % TailleCarre < TailleCarre / 2) {
            x = x - x % TailleCarre - TailleCarre;
        } else {
            x = x - x % TailleCarre;
        }
        if (y % TailleCarre < TailleCarre / 2) {
            y = y - y % TailleCarre - TailleCarre;
        } else {
            y = y - y % TailleCarre;
        }
        draggedTourImage = image;
        repaint();
    }

    public void drawProjectileWithCircleRadius(Graphics2D gq, Graphics g) {
        if (draggedTourImage != null && toursSelected != null) {
            g.drawImage(draggedTourImage, mouseX, mouseY, TailleCarre * 2, TailleCarre * 2, null);
            drawProjectileDragMouseCircle(gq, mouseX, mouseY);

        }
    }

    public void drawButtonsSelectedTower(Graphics2D gq) {
        if (tourController.getLastClickedTower() != null && tourController.isDisplayButtons()) {

            int projectileX = tourController.getLastClickedTower().getX();
            int projectileY = tourController.getLastClickedTower().getY();
            gq.drawImage(tourController.getAmelioratePic(), projectileX - 2 * TailleCarre - 5,
                    projectileY - 2 * TailleCarre, 2 * TailleCarre, 2 * TailleCarre, null);
            gq.drawImage(tourController.getSellPic(), projectileX, projectileY - 2 * TailleCarre, 2 * TailleCarre,
                    2 * TailleCarre, null);
            gq.drawImage(tourController.getReturnPic(), projectileX + 2 * TailleCarre + 5,
                    projectileY - 2 * TailleCarre, 2 * TailleCarre, 2 * TailleCarre, null);

            String ameliorationPrice = String.valueOf(tourController.getLastClickedTower().getAmeliorationValue());
            String sellMoney = String.valueOf(tourController.getLastClickedTower().getSellValue());
            String niveau = String
                    .valueOf(tourController.getLastClickedTower().getModifAttribut().getNiveauAmelioration());
            gq.setColor(Color.BLACK);
            gq.setFont(new Font("Arial", Font.PLAIN, 20));
            gq.drawString(ameliorationPrice, projectileX - TailleCarre - 15, projectileY - 3);
            gq.drawString(sellMoney, projectileX + 22, projectileY - 3);
            gq.drawString(niveau, projectileX + TailleCarre - 5, projectileY + 3 * TailleCarre);

            drawCircleButtonDisposition(gq, projectileX, projectileY);

        }
    }

    public void paintComponent(Graphics g) { // dessiner après chaque update

        super.paintComponent(g);
        Graphics2D gq = (Graphics2D) g;
        // quadrillage
        drawQuadrillage(gq);
        barreChoixProjectiles(gq);
        createPath(gq);
        drawProjectile(gq);
        drawProjectileWithCircleRadius(gq, g);
        drawInfoKeayboard(gq);
        drawButtonsSelectedTower(gq);

        Monstres premier = null;
        if (liste_monstres.isEmpty() == false) {
            premier = liste_monstres.premier();
        }
        /*
         * if(premier.HP>0) {
         * System.out.println(premier);
         * 
         * }
         * else{
         * System.out.println("fin perso");
         * premier = null;
         * }
         */

        // dessine les zombies
        if (premier != null) {

            premier.deplacementZombie();
            gq.drawImage(premier.image, premier.x, premier.y, TailleCarre, TailleCarre, null);
            gq.setColor(Color.red);
            // sa barre de vie
            gq.fillRect(premier.x + TailleCarre / 2 - premier.HP / 2, premier.y - 4, premier.HP, 3);
            while (liste_monstres.suivant((premier)) != null) {
                drawLineBetweenMonsterProjectile(gq, premier);
                if (premier != null) {

                    premier = liste_monstres.suivant(premier);

                    premier.deplacementZombie();
                    gq.drawImage(premier.image, premier.x, premier.y, TailleCarre, TailleCarre, null);
                    gq.setColor(Color.red);
                    // sa barre de vie
                    gq.fillRect(premier.x + TailleCarre / 2 - premier.HP / 2, premier.y - 4, premier.HP, 3);
                }

            }
        }
        tunel.updateVies();
        if (this.lvl == 1)

        {
            gq.drawImage(tunel.Imagevies, tunel.x1, tunel.y1 + TailleCarre * 3, TailleCarre * 3, TailleCarre, null);
        }
        if (this.lvl == 2) {
            gq.drawImage(tunel.Imagevies, tunel.x2, tunel.y2 + TailleCarre * 3, TailleCarre * 3, TailleCarre, null);
        }

        // zombie.deplacementZombie();
        // gq.drawImage(zombie.image, zombie.x, zombie.y, TailleCarre, TailleCarre,
        // null);
        // gq.setColor(Color.red);
        // sa barre de vie
        // gq.fillRect(zombie.x + TailleCarre/2 - zombie.HP/2, zombie.y-4, zombie.HP,
        // 3);

        // pour afficher le chrono
        printChrono(gq);

        // pour afficher l'argent restant
        printRestOfMoney(gq);

        endOfTheGame(gq);

        gq.dispose();
    }

}
