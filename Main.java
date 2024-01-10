import javax.swing.JFrame;

import Vue.GamePanel;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Tower Defense level 1");

        GamePanel gamePanel = new GamePanel(1);

        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startThread();

        gamePanel.setGameOverListener(() -> {
            
            GamePanel newGamePanel = new GamePanel(2);

            window.setTitle("Tower Defense level 2");
            window.getContentPane().removeAll();
            window.add(newGamePanel);
            window.revalidate();
            window.repaint();

            newGamePanel.startThread();
        });
    }

}
