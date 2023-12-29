import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class KeyboardController extends JFrame {
    private final GamePanel gamePanel;
    public KeyboardController(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    public void initializeKeyboardListener() {
        gamePanel.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                System.out.println("Caractère tapé : " + keyChar);

                if (keyChar == 'p' || keyChar == 'P') {
                    System.out.println("p clicked");
                    gamePanel.miseEnPauseDuJeu();
                }
            }
        });
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
    }
}
