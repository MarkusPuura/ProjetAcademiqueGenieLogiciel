import javax.swing.JFrame;

import Vue.GamePanel;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Tower Defense");

        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startThread();

        gamePanel.setGameOverListener(() -> {
            
            GamePanel newGamePanel = new GamePanel();

            window.getContentPane().removeAll();
            window.add(newGamePanel);
            window.revalidate();
            window.repaint();

            newGamePanel.startThread();
        });
    }

}
