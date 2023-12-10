import java.awt.*;

public class Tunel {
    private static Tunel instance; // Instance unique de Tunnel
    private int x;
    private int y;
    private int width;
    private int height;

    private Tunel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public static Tunel getInstance(int x, int y, int width, int height) {
        if (instance == null) {
            instance = new Tunel(x, y, width, height);
        }
        return instance;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.darkGray);
        g2d.fillRect(x, y, width, height);
    }
}
