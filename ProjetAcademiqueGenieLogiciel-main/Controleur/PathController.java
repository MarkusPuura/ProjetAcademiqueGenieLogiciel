package Controleur;
public class PathController {
    private final int TailleCarre;
    private final int LargeurEcran;
    private final int HauteurEcran;

    

    public PathController(int TailleCarre, int LargeurEcran, int HauteurEcran) {
        this.TailleCarre = TailleCarre;
        this.LargeurEcran = LargeurEcran;
        this.HauteurEcran = HauteurEcran;
    }
    public boolean isOnPath(int x, int y) {
        // Vérifie si les coordonnées (x, y) se trouvent sur le chemin

        // Coordonnées des rectangles du chemin
        int[][] rectangles = {
            {0, 15*TailleCarre - TailleCarre/2, LargeurEcran, TailleCarre*7},

            {0, 0, LargeurEcran, TailleCarre + TailleCarre/2},
            {0, 2*TailleCarre - TailleCarre/2, LargeurEcran - 2*TailleCarre + TailleCarre/2, 2*TailleCarre},
            {LargeurEcran - 3*TailleCarre - TailleCarre/2, 3*TailleCarre, 2*TailleCarre, 12*TailleCarre + TailleCarre/2},
            {17*TailleCarre - TailleCarre/2, 14*TailleCarre - TailleCarre/2, 17*TailleCarre, 2*TailleCarre},
            {17*TailleCarre - TailleCarre/2, 8*TailleCarre - TailleCarre/2, 2*TailleCarre, 7*TailleCarre},
            {6*TailleCarre - TailleCarre/2, 8*TailleCarre - TailleCarre/2, 12*TailleCarre, 2*TailleCarre},
            {3*TailleCarre - TailleCarre/2, 6*TailleCarre - TailleCarre/2, 4*TailleCarre, 5*TailleCarre},
            //zone vers la tour  :
            //{0, 0, 6*TailleCarre, HauteurEcran},
        };

        // Vérifie si les coordonnées (x, y) se trouvent dans un des rectangles du chemin
        for (int[] rect : rectangles) {
            int rectX = rect[0];
            int rectY = rect[1];
            int rectWidth = rect[2];
            int rectHeight = rect[3];

            if (x >= rectX && x <= rectX + rectWidth && y >= rectY && y <= rectY + rectHeight) {
                return true;
            }
        }

        return false;
    }
}
