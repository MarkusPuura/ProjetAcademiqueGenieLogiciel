
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    final int TailleOriginalPersonnages = 16; // personnages + objets de taille 16*16
    final int echelle = 2;
    final int TailleCarre = TailleOriginalPersonnages * echelle;

    final int ColonnesEcran = 35;
    final int LignesEcran = 20;
    final int LargeurEcran = ColonnesEcran * TailleCarre; // 35 * (2*16) =
    final int HauteurEcran = LignesEcran * TailleCarre; // 20 * (2*16) =

    int FPS = 60;
    Chrono chrono = new Chrono();
    Kama kama = new Kama();
    int fin;

    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(LargeurEcran, HauteurEcran));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true); //ameliorer affichage
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

    int x = 100;
    int y = TailleCarre*2;

    public void update(){
        x++;

        fin = chrono.UpdateChrono(FPS);
    }

    public void paintComponent(Graphics g){     //dessiner après chaque update

        super.paintComponent(g);
        Graphics2D gq = (Graphics2D)g;

        //quadrillage
        gq.setColor(Color.lightGray);
        for (int i = 1; i <= ColonnesEcran; i++){
            gq.fillRect(i*TailleCarre, 0, 1, HauteurEcran);
        }
        for (int i = 1; i <= LignesEcran; i++){
            gq.fillRect(0, i*TailleCarre, LargeurEcran, 1);
        }

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
        gq.setColor(Color.lightGray);   // la barre en haut et en bas
        gq.fillRect(0, 0, LargeurEcran, 1*TailleCarre);
        gq.fillRect(0, HauteurEcran - 4*TailleCarre, LargeurEcran, 4*TailleCarre);

        gq.setColor(Color.red);
        gq.fillRect(x, y, TailleCarre, TailleCarre);

        //pour afficher le chrono
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
        
        //pour afficher l'argent restant
        String afficherKama = "Kama: " + kama.portefeuille;
        gq.drawString(afficherKama, LargeurEcran - 8*TailleCarre, 1*TailleCarre - 4);


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
        gq.dispose();
    }

}
