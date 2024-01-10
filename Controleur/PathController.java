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
    public boolean isOnPath(int x, int y, int lvl) {
        // Vérifie si les coordonnées (x, y) se trouvent sur le chemin


        // Coordonnées des rectangles du chemin dans lvl1
        int[][] rectangles1 = {
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
        
   
        // Coordonnées des rectangles du chemin dans lvl2
        int[][] rectangles2 = {
            {0, 15*TailleCarre - TailleCarre/2, LargeurEcran, TailleCarre*7},

            {0, 0, LargeurEcran, TailleCarre + TailleCarre/2},
            {0, 2*TailleCarre - TailleCarre/2, 16*TailleCarre + TailleCarre/2, 2*TailleCarre},
            {15*TailleCarre - TailleCarre/2, 3*TailleCarre, 2*TailleCarre, 8*TailleCarre + TailleCarre/2},
            {15*TailleCarre - TailleCarre/2, 11*TailleCarre - TailleCarre/2, 15*TailleCarre, TailleCarre*2},
            {30*TailleCarre - TailleCarre/2, 9*TailleCarre - TailleCarre/2, 4*TailleCarre, TailleCarre*5},
            
            //zone vers la tour  :
            //{0, 0, 6*TailleCarre, HauteurEcran},
        };
        

        // Vérifie si les coordonnées (x, y) se trouvent dans un des rectangles du chemin
        if (lvl == 1){
            for (int[] rect : rectangles1) {
                int rectX = rect[0];
                int rectY = rect[1];
                int rectWidth = rect[2];
                int rectHeight = rect[3];

                if (x >= rectX && x <= rectX + rectWidth && y >= rectY && y <= rectY + rectHeight) {
                    return true;
                }
            }
        }
        if (lvl == 2){
            for (int[] rect : rectangles2) {
                int rectX = rect[0];
                int rectY = rect[1];
                int rectWidth = rect[2];
                int rectHeight = rect[3];

                if (x >= rectX && x <= rectX + rectWidth && y >= rectY && y <= rectY + rectHeight) {
                    return true;
                }
            }
        }
        return false;
    }
}
