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
            //{0, TailleCarre, LargeurEcran - 2 * TailleCarre, TailleCarre},
            //{0, TailleCarre * 2, LargeurEcran - 2 * TailleCarre, TailleCarre},
            //{0, TailleCarre*3, LargeurEcran - 3*TailleCarre, 1},
            //{LargeurEcran - 2 * TailleCarre, TailleCarre * 2, 2, TailleCarre * 13},
            //{LargeurEcran - 3 * TailleCarre, TailleCarre * 3, TailleCarre, TailleCarre * 11},
            //{18 * TailleCarre, 14 * TailleCarre, 14 * TailleCarre, TailleCarre},
            //{17 * TailleCarre, 15 * TailleCarre, 16 * TailleCarre, 2},

            {0, 15*TailleCarre - TailleCarre/2, LargeurEcran, TailleCarre*7},

            //{17 * TailleCarre, 9 * TailleCarre, 2, TailleCarre * 6},
            //{18 * TailleCarre, 8 * TailleCarre, TailleCarre, TailleCarre * 6},
            //{6 * TailleCarre, 8 * TailleCarre, 12 * TailleCarre, TailleCarre},
            //{6 * TailleCarre, 9 * TailleCarre, 11 * TailleCarre, 2},
            //{0, 0, LargeurEcran, TailleCarre},
            //{0, HauteurEcran - 4 * TailleCarre, LargeurEcran, 4 * TailleCarre},

            //{0, TailleCarre*2+1, LargeurEcran - 2*TailleCarre, TailleCarre-1},
            //{LargeurEcran - 3*TailleCarre+2, TailleCarre*3, TailleCarre-2, TailleCarre*11+1},
            //{17*TailleCarre+2, 14*TailleCarre+1, 16*TailleCarre-2, TailleCarre-1},
            //{17*TailleCarre+2, 8*TailleCarre+1, TailleCarre-2, TailleCarre*6},
            //{6*TailleCarre, 8*TailleCarre+1, 12*TailleCarre-1, TailleCarre-1},
            {0, 0, LargeurEcran, TailleCarre + TailleCarre/2},
            {0, 2*TailleCarre - TailleCarre/2, LargeurEcran - 2*TailleCarre + TailleCarre/2, 2*TailleCarre},
            {LargeurEcran - 3*TailleCarre - TailleCarre/2, 3*TailleCarre, 2*TailleCarre, 12*TailleCarre + TailleCarre/2},
            {17*TailleCarre - TailleCarre/2, 14*TailleCarre - TailleCarre/2, 17*TailleCarre, 2*TailleCarre},
            {17*TailleCarre - TailleCarre/2, 8*TailleCarre - TailleCarre/2, 2*TailleCarre, 7*TailleCarre},
            {6*TailleCarre - TailleCarre/2, 8*TailleCarre - TailleCarre/2, 12*TailleCarre, 2*TailleCarre},
            {3*TailleCarre - TailleCarre/2, 6*TailleCarre - TailleCarre/2, 4*TailleCarre, 5*TailleCarre},
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
