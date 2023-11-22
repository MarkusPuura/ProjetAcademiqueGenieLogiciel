package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    final int TailleOriginalPersonnages = 16; // personnages + objets de taille 16*16
    final int echelle = 2;
    final int TailleCarre = TailleOriginalPersonnages * echelle;

    final int ColonnesEcran = 35;
    final int LignesEcran = 20;
    final int LargeurEcran = ColonnesEcran * TailleCarre; // 35 * (2*16) =
    final int HauteurEcran = LignesEcran * TailleCarre; // 20 * (2*16) =

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
        int FPS = 60;
        double tempsUpdate = 1000000000/FPS;
        double ProchainImage = System.nanoTime() + tempsUpdate;

        while(gameThread != null){

            update();
            repaint();

            try{
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
    }

    int x = 100;
    int y = TailleCarre*2;

    public void update(){
        x++;
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

        gq.setColor(Color.red);
        gq.fillRect(x, y, TailleCarre, TailleCarre);
        gq.dispose();

    }

}
