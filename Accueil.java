import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import Modele.Musique;
import Vue.GamePanel;

public class Accueil {
    private static final int PANEL_WIDTH = 35 * (2 * 16);
    private static final int PANEL_HEIGHT = 20 * (2 * 16);
    public BufferedImage fond, bouton;
    private JFrame homeWindow;
    private JPanel homePanel;
    private JButton startButton;
    private Musique m;

    public Accueil() {
        GetImage();
        this.homeWindow = creerPageAccueil();
        this.homePanel = createHomePanel();
        this.startButton = createStartButton();
        this.m = new Musique();

    }

    public void GetImage() {
        try {
            fond = ImageIO.read(getClass().getResourceAsStream("images/pageAccueuil/PageAccueil.png"));
            bouton = ImageIO.read(getClass().getResourceAsStream("images/pageAccueuil/BoutonStart.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void afficherAccueil() {
        homePanel.add(startButton);
        homeWindow.add(homePanel);
        homeWindow.pack();
        homeWindow.setLocationRelativeTo(null);
        homeWindow.setVisible(true);
        ajouterImageFond();

    }

    private JFrame creerPageAccueil() {
        JFrame homeWindow = new JFrame();
        homeWindow.setResizable(false);
        homeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeWindow.setTitle("Tower Defense - Home Page");
        return homeWindow;
    }

    private JPanel createHomePanel() {
        JPanel homePanel = new JPanel();
        homePanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        OverlayLayout overlayLayout = new OverlayLayout(homePanel);
        homePanel.setLayout(overlayLayout);
        return homePanel;
    }

    private JButton createStartButton() {

        ImageIcon buttonIcon = new ImageIcon(bouton);
        JButton startButton = new JButton(buttonIcon);
        startButton.setBounds(2000, 0, 50, 50);
        styliserBouton(startButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.stopperMusique();

                startGame();
            }
        });
        return startButton;
    }

    private void styliserBouton(JButton bouton) {
        bouton.setFont(new Font("Arial", Font.BOLD, 20));
        bouton.setBackground(new Color(0, 0, 0, 0));
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
        bouton.setBorderPainted(false);
        bouton.setContentAreaFilled(false);
        bouton.setOpaque(false);
    }

    public void startGame() {
        JFrame window = new JFrame();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Tower Defense level 1");

        GamePanel gamePanel = new GamePanel(1);

        window.add(gamePanel);
        // window.add(startButton);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startThread();

        gamePanel.setGameOverListener(() -> {

            startNewGame(window);
        });
    }

    private static void startNewGame(JFrame window) {
        GamePanel newGamePanel = new GamePanel(2);

        window.setTitle("Tower Defense level 2");
        window.getContentPane().removeAll();
        window.add(newGamePanel);
        window.revalidate();
        window.repaint();

        newGamePanel.startThread();
    }

    private void ajouterImageFond() {
        ImageIcon originalImageIcon = new ImageIcon(fond);
        Image originalImage = originalImageIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(PANEL_WIDTH, PANEL_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel backgroundLabel = new JLabel(scaledImageIcon);
        homePanel.add(backgroundLabel);
        homePanel.revalidate();
        homePanel.repaint();
    }
}
