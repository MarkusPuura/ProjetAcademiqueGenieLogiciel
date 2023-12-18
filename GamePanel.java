import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
    final int TailleOriginalPersonnages = 16; // personnages + objets de taille 16*16
    final int echelle = 2;
    final int TailleCarre = TailleOriginalPersonnages * echelle;

    final int ColonnesEcran = 35;
    final int LignesEcran = 20;
    final int LargeurEcran = ColonnesEcran * TailleCarre; // 35 * (2*16) =
    final int HauteurEcran = LignesEcran * TailleCarre; // 20 * (2*16) =

    int niveau = 1; //difficulté du jeu
    int FPS = 60;
    Chrono chrono = new Chrono();
    Kama kama = new Kama();
    int fin;          // quand passe à 1: le joueur a gagné, si passe à 2: il a perdu.

    Tunel tunel;
    Thread gameThread;
    Tours1 tours1inventaire = new Tours1(30, 3, 1, 6*TailleCarre, HauteurEcran - 3*TailleCarre,30);
    TourController tourController;
    Tours1 toursSelected = null;
    int nbClics = 0;
    private MouseController mouseController;



    public GamePanel(){
        this.setPreferredSize(new Dimension(LargeurEcran, HauteurEcran));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true); //ameliorer affichage
        createTunel(); // Création du tunnel à la fin du chemin
        tourController = new TourController();
        PathController pathController = new PathController(TailleCarre, LargeurEcran, HauteurEcran);
        mouseController = new MouseController(this,pathController);
        mouseController.initializeMouseListener();

    }

    private void createTunel() {
        tunel = Tunel.getInstance(3*TailleCarre, 6*TailleCarre);
    }


    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double tempsUpdate = 1000000000/FPS;
        double ProchainImage = System.nanoTime() + tempsUpdate;

        while(gameThread != null && fin == 0){

            update();
            repaint();

            try{    // pour determiner le nbr d'images par seconde
                double tempsRestant = ProchainImage - System.nanoTime();
                tempsRestant = tempsRestant/1000000;    // en millisecondes
                if(tempsRestant < 0){
                    tempsRestant = 0;
                }
                Thread.sleep((long) tempsRestant);
                ProchainImage += tempsUpdate;
            } catch(InterruptedException e){
                e.printStackTrace();
            }

        }
        update();
    }


    GenerationRandomMonstres random = new GenerationRandomMonstres(niveau);
    ListeMonstresVivants liste_monstres = new ListeMonstresVivants();
    //Monstres zombie = new Zombie(20, 4, 5, 0, TailleCarre*2);
    //liste_monstres.ajouterEnFin(zombie);
    
    int debut = 0;
    public void update(){
        
        //cree monstres via la GenerationRandomMonstres
        random.generermonstre(chrono, TailleCarre, liste_monstres, debut);          //ITERATOR (Design pattern)
        debut = 1;  //si pas de monstre ça ne marche pas bizzarement...
        Monstres iterateur = liste_monstres.premier();
        if (iterateur != null){
            iterateur.updateMonstre(TailleCarre, HauteurEcran, LargeurEcran);
            while (liste_monstres.suivant((iterateur)) != null){
                iterateur = liste_monstres.suivant(iterateur);
                iterateur.updateMonstre(TailleCarre, HauteurEcran, LargeurEcran);
            }
        }

        // Vérification des collisions entre projectiles et monstres
        for (Projectile projectile : tourController.getTowersList()) {
            if (projectile.isActive()) {
                //System.out.println("for boucle ");
                Monstres iterMonstre = liste_monstres.premier();
                while (iterMonstre != null) {
                   // System.out.println("while boucle ");

                    projectile.checkCollision(iterMonstre);
                    iterMonstre = liste_monstres.suivant(iterMonstre);
                }
            }
        }
        
        //supprime les monstres qui n'ont plus d'HP et ceux qui sont arrivés à la fin
        Monstres suivant;
        iterateur = liste_monstres.premier();
        int suppr = 0;
        if (iterateur != null){
            if (iterateur.HP <= 0){
                kama.portefeuille+= iterateur.kama;     //on gagne le kama pour avoir tué le monstre
                liste_monstres.supprimer(iterateur);
                suppr = 1;
            }
            if (iterateur.fin == 1){               //si il est arrivé au chateau
                liste_monstres.supprimer(iterateur);
                suppr = 1;
                tunel.vies--;
            }
        }
        iterateur = liste_monstres.premier();
        if (iterateur != null){
            while (liste_monstres.suivant((iterateur)) != null){
                if (suppr == 0){
                    iterateur = liste_monstres.suivant(iterateur);
                    if (iterateur.HP <= 0){
                        suivant = liste_monstres.suivant(iterateur);
                        liste_monstres.supprimer(iterateur);
                        iterateur = suivant;
                        suppr = 1;
                    }
                    if (iterateur.fin == 1){        //si il est arrivé au chateau
                        suivant = liste_monstres.suivant(iterateur);
                        liste_monstres.supprimer(iterateur);
                        iterateur = suivant;
                        suppr = 1;
                        tunel.vies--;
                    }
                }
                else{
                    suppr = 0;
                    if (iterateur.HP <= 0){
                        suivant = liste_monstres.suivant(iterateur);
                        liste_monstres.supprimer(iterateur);
                        iterateur = suivant;
                        suppr = 1;
                    }
                }
            }
        }


        fin = chrono.UpdateChrono(FPS);
        if (tunel.vies == 0){
            fin = 2;    //perdu
        }
        
    }

    

    public void drawQuadrillage(Graphics2D gq){
        Color vert = new Color(180, 255, 180);
        gq.setColor(vert);   //fond vert
        gq.fillRect(0, 0, LargeurEcran, HauteurEcran);

        gq.setColor(Color.lightGray);
        for (int i = 1; i <= ColonnesEcran; i++){
            gq.fillRect(i*TailleCarre, 0, 1, HauteurEcran);
        }
        for (int i = 1; i <= LignesEcran; i++){
            gq.fillRect(0, i*TailleCarre, LargeurEcran, 1);
        }
    }
    public void createPath(Graphics2D gq){
        //chemin
        gq.setColor(Color.black);
        gq.fillRect(0, TailleCarre*2, LargeurEcran - 2*TailleCarre, 1);
        gq.fillRect(0, TailleCarre*3, LargeurEcran - 3*TailleCarre, 1);
        gq.fillRect(LargeurEcran - 2*TailleCarre, TailleCarre*2, 2, TailleCarre*13);
        gq.fillRect(LargeurEcran - 3*TailleCarre, TailleCarre*3, 2, TailleCarre*11);
        gq.fillRect(18*TailleCarre, 14*TailleCarre, 14*TailleCarre, 1);
        gq.fillRect(17*TailleCarre, 15*TailleCarre, 16*TailleCarre, 1);
        gq.fillRect(17*TailleCarre, 9*TailleCarre, 2, TailleCarre*6);
        gq.fillRect(18*TailleCarre, 8*TailleCarre, 2, TailleCarre*6);
        gq.fillRect(6*TailleCarre, 8*TailleCarre, 12*TailleCarre, 1);
        gq.fillRect(6*TailleCarre, 9*TailleCarre, 11*TailleCarre, 1);
        Color gris = new Color(200, 200, 200);
        gq.setColor(gris);
        gq.fillRect(0, TailleCarre*2+1, LargeurEcran - 2*TailleCarre, TailleCarre-1);
        gq.fillRect(LargeurEcran - 3*TailleCarre+2, TailleCarre*3, TailleCarre-2, TailleCarre*11+1);
        gq.fillRect(17*TailleCarre+2, 14*TailleCarre+1, 16*TailleCarre-2, TailleCarre-1);
        gq.fillRect(17*TailleCarre+2, 8*TailleCarre+1, TailleCarre-2, TailleCarre*6);
        gq.fillRect(6*TailleCarre, 8*TailleCarre+1, 12*TailleCarre-1, TailleCarre-1);

        gq.setColor(Color.lightGray);   // la barre en haut et en bas
        gq.fillRect(0, 0, LargeurEcran, TailleCarre);
        gq.fillRect(0, HauteurEcran - 4*TailleCarre, LargeurEcran, 4*TailleCarre);

        //dessine le chateau
        gq.drawImage(tunel.Imagechateau, tunel.x, tunel.y, TailleCarre*3, TailleCarre*3, null);

        //dessine tours dans inventaire

        gq.drawImage(tours1inventaire.image, tours1inventaire.x, tours1inventaire.y, TailleCarre*2, TailleCarre*2, null);
        gq.setColor(Color.BLACK);
        gq.setFont(new Font("Arial", Font.PLAIN, 15));
        gq.drawString("30 Kama", 6*TailleCarre, HauteurEcran - TailleCarre/2);

    }

    public void endOfTheGame(Graphics2D gq){
        if (fin == 1){  //Si le joueur a gagné (dernier update)
            String messageVictoire = "Bravo vous avez gagné :)";
            gq.setColor(Color.red);
            gq.setFont(new Font("Arial", Font.PLAIN, 75));
            gq.drawString(messageVictoire, 5*TailleCarre, HauteurEcran/2);
        }
        if (fin == 2){  //Si le joueur a perdu (dernier update)
            String messageDefaite = "Bravo vous avez perdu :(";
            gq.setColor(Color.red);
            gq.setFont(new Font("Arial", Font.PLAIN, 75));
            gq.drawString(messageDefaite, 5*TailleCarre, HauteurEcran/2);
        }
    }

    public void printChrono(Graphics2D gq){
        String afficherChrono;
        if (chrono.sec < 10){
            afficherChrono = chrono.min + ":0" + chrono.sec;
        }
        else{
            afficherChrono = chrono.min + ":" + chrono.sec;
        }
        gq.setColor(Color.BLACK);
        gq.setFont(new Font("Arial", Font.PLAIN, 25));
        gq.drawString(afficherChrono, LargeurEcran - 3*TailleCarre, 1*TailleCarre - 4);
    }
    public void printRestOfMoney(Graphics2D gq){
        String afficherKama = "Kama: " + kama.portefeuille;
        gq.drawString(afficherKama, LargeurEcran - 8*TailleCarre, 1*TailleCarre - 4);
    }
    public void barreChoixProjectiles(Graphics2D gq){
        // Barre en bas de couleur grise foncée
        Color brun = new Color(97, 72, 54);
        gq.setColor(brun);
        gq.fillRect(0, HauteurEcran - 4*TailleCarre - TailleCarre/4, LargeurEcran, TailleCarre/4);
    }
    public void drawProjectile(Graphics2D gq){
        for (Projectile projectile : tourController.getTowersList()) {
            if (projectile.isActive()) {
                //projectile.draw(gq);
                gq.drawImage(projectile.image, projectile.x, projectile.y, TailleCarre*2, TailleCarre*2, null);

            }
        }
    }
    

    public void paintComponent(Graphics g){     //dessiner après chaque update

        super.paintComponent(g);
        Graphics2D gq = (Graphics2D)g;
        //quadrillage
        drawQuadrillage(gq);
        barreChoixProjectiles(gq);
        createPath(gq);
        drawProjectile(gq);

        //dessine les zombies
        Monstres premier = liste_monstres.premier();
        if (premier != null){
            premier.deplacementZombie();
            gq.drawImage(premier.image, premier.x, premier.y, TailleCarre, TailleCarre, null);
            gq.setColor(Color.red);
            //sa barre de vie
            gq.fillRect(premier.x + TailleCarre/2 - premier.HP/2, premier.y-4, premier.HP, 3);
            while (liste_monstres.suivant((premier)) != null){
                premier = liste_monstres.suivant(premier);

                premier.deplacementZombie();
                gq.drawImage(premier.image, premier.x, premier.y, TailleCarre, TailleCarre, null);
                gq.setColor(Color.red);
                //sa barre de vie
                gq.fillRect(premier.x + TailleCarre/2 - premier.HP/2, premier.y-4, premier.HP, 3);


            }
        }
        tunel.updateVies();
        gq.drawImage(tunel.Imagevies, tunel.x, tunel.y + TailleCarre*3, TailleCarre*3, TailleCarre, null);

        //zombie.deplacementZombie();
        //gq.drawImage(zombie.image, zombie.x, zombie.y, TailleCarre, TailleCarre, null);
        //gq.setColor(Color.red);
        //sa barre de vie
        //gq.fillRect(zombie.x + TailleCarre/2 - zombie.HP/2, zombie.y-4, zombie.HP, 3);

        //pour afficher le chrono
        printChrono(gq);

        //pour afficher l'argent restant
        printRestOfMoney(gq);


        //fin
        endOfTheGame(gq);
        gq.dispose();
    }


}
