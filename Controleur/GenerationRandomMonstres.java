package Controleur;

import Modele.*;
import Modele.differentsMonstres.*;

public class GenerationRandomMonstres {
    int niveau;

    public GenerationRandomMonstres(int niveau){
        this.niveau = niveau;
    }

    public void generermonstre(Chrono chrono, int TailleCarre, ListeMonstresVivants liste_monstres, int debut, int lvl){
        long temps = System.nanoTime();

        if (lvl == 1){
            if (debut == 0){
                Monstres zombie = new Zombie(40, 1, 5, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(zombie);
            }
            

            if (chrono.min < 5){
                if (temps%(21000/niveau) == 0){
                    Monstres zombie1 = new Zombie(40, 1, 3, 0, TailleCarre*2);      //Attention: la vitess peut que être 
                    liste_monstres.ajouterEnFin(zombie1);                                             // = à 1; 2; 4; 8; 16; 0.5; 0.25
                }
                if (temps%(40000/niveau) == 0){
                    Monstres serpent1 = new Serpent(20, 2, 2, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(serpent1);
                }
            }
            if (chrono.min < 4){
                if (temps%(26000/niveau) == 0){
                    Monstres zombie1 = new Zombie(40, 1, 3, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(zombie1);
                }
                if (temps%(50000/niveau) == 0){
                    Monstres serpent1 = new Serpent(20, 2, 2, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(serpent1);
                }
                if (temps%(70000/niveau) == 0){
                    Monstres goleme1 = new Goleme(100, 1, 1, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(goleme1);
                }
            }
            if (chrono.min < 3){
                if (temps%(32000/niveau) == 0){
                    Monstres zombie1 = new Zombie(40, 1, 3, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(zombie1);
                }
                if (temps%(37000/niveau) == 0){
                    Monstres serpent1 = new Serpent(20, 2, 2, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(serpent1);
                }
                if (temps%(52000/niveau) == 0){
                    Monstres goleme1 = new Goleme(100, 1, 1, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(goleme1);
                }
            }
            if (chrono.min < 2){
                if (temps%(23000/niveau) == 0){
                    Monstres zombie1 = new Zombie(40, 1, 3, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(zombie1);
                }
                if (temps%(36000/niveau) == 0){
                    Monstres serpent1 = new Serpent(20, 2, 2, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(serpent1);
                }
                if (temps%(35000/niveau) == 0){
                    Monstres goleme1 = new Goleme(100, 1, 1, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(goleme1);
                }
            }
            if (chrono.min < 1){
                if (temps%(19000/niveau) == 0){
                    Monstres zombie1 = new Zombie(40, 1, 3, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(zombie1);
                }
                if (temps%(22000/niveau) == 0){
                    Monstres serpent1 = new Serpent(20, 2, 2, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(serpent1);
                }
                if (temps%(20000/niveau) == 0){
                    Monstres goleme1 = new Goleme(100, 1, 1, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(goleme1);
                }
            }
        }
        if (lvl == 2){
            if (debut == 0){
                Monstres momie = new Momie(40, 1, 5, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(momie);
            }
            

            if (chrono.min < 5){
                if (temps%(22000/niveau) == 0){
                    Monstres momie1 = new Momie(40, 1, 3, 0, TailleCarre*2);       
                    liste_monstres.ajouterEnFin(momie1);                                             
                }
                if (temps%(41000/niveau) == 0){
                    Monstres cobra1 = new Cobra(20, 2, 2, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(cobra1);
                }
            }
            if (chrono.min < 4){
                if (temps%(27000/niveau) == 0){
                    Monstres momie1 = new Momie(40, 1, 3, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(momie1);
                }
                if (temps%(51000/niveau) == 0){
                    Monstres cobra1 = new Cobra(20, 2, 2, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(cobra1);
                }
                if (temps%(72000/niveau) == 0){
                    Monstres islamiste1 = new Islamiste(100, 1, 1, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(islamiste1);
                }
            }
            if (chrono.min < 3){
                if (temps%(32000/niveau) == 0){
                    Monstres momie1 = new Momie(40, 1, 3, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(momie1);
                }
                if (temps%(37000/niveau) == 0){
                    Monstres cobra1 = new Cobra(20, 2, 2, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(cobra1);
                }
                if (temps%(52000/niveau) == 0){
                    Monstres islamiste1 = new Islamiste(100, 1, 1, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(islamiste1);
                }
            }
            if (chrono.min < 2){
                if (temps%(23000/niveau) == 0){
                    Monstres momie1 = new Momie(40, 1, 3, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(momie1);
                }
                if (temps%(36000/niveau) == 0){
                    Monstres cobra1 = new Cobra(20, 2, 2, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(cobra1);
                }
                if (temps%(35000/niveau) == 0){
                    Monstres islamiste1 = new Islamiste(100, 1, 1, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(islamiste1);
                }
            }
            if (chrono.min < 1){
                if (temps%(19000/niveau) == 0){
                    Monstres momie1 = new Momie(40, 1, 3, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(momie1);
                }
                if (temps%(22000/niveau) == 0){
                    Monstres cobra1 = new Cobra(20, 2, 2, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(cobra1);
                }
                if (temps%(20000/niveau) == 0){
                    Monstres islamiste1 = new Islamiste(100, 1, 1, 0, TailleCarre*2);
                    liste_monstres.ajouterEnFin(islamiste1);
                }
            }
        }
    }

    public void genererbebe(int TailleCarre, ListeMonstresVivants liste_monstres, int x, int y, int lvl){
        if (x%32 > 16){
            while (x%32 != 0){
                x++;
            }
        }
        else{
            while (x%32 != 0){
                x--;
            }
        }
        if (y%32 > 16){
            while (y%32 != 0){
                y++;
            }
        }
        else{
            while (y%32 != 0){
                y--;
            }
        }
        
        if (lvl == 1){
            Monstres bebe1 = new Bebe(10, 2, 1, x, y);
            if (x != 17*TailleCarre && y != 8*TailleCarre){
                liste_monstres.ajouterEnFin(bebe1);
            }
            

            if (y == TailleCarre*2){
                Monstres bebe2 = new Bebe(10, 2, 1, x-10, y);
                liste_monstres.ajouterEnFin(bebe2);
                Monstres bebe3 = new Bebe(10, 2, 1, x-20, y);
                liste_monstres.ajouterEnFin(bebe3);
            }
            else if (x == TailleCarre*32){
                if (y < 4*TailleCarre){
                    Monstres bebe2 = new Bebe(10, 2, 1, x-10, 2*TailleCarre);
                    liste_monstres.ajouterEnFin(bebe2);
                    Monstres bebe3 = new Bebe(10, 2, 1, x-20, 2*TailleCarre);
                    liste_monstres.ajouterEnFin(bebe3);
                }
                else{
                    Monstres bebe2 = new Bebe(10, 2, 1, x, y-10);
                    liste_monstres.ajouterEnFin(bebe2);
                    Monstres bebe3 = new Bebe(10, 2, 1, x, y-20);
                    liste_monstres.ajouterEnFin(bebe3);
                }
            }
            else if (y == 14*TailleCarre){
                if (x > 31*TailleCarre){
                    Monstres bebe2 = new Bebe(10, 2, 1, 32*TailleCarre, y - 10);
                    liste_monstres.ajouterEnFin(bebe2);
                    Monstres bebe3 = new Bebe(10, 2, 1, 32*TailleCarre, y - 20);
                    liste_monstres.ajouterEnFin(bebe3);
                }
                else{
                    Monstres bebe2 = new Bebe(10, 2, 1, x + 10, y);
                    liste_monstres.ajouterEnFin(bebe2);
                    Monstres bebe3 = new Bebe(10, 2, 1, x + 20, y);
                    liste_monstres.ajouterEnFin(bebe3);
                }
            }
        
        //else if (x == 17*TailleCarre){        // impolssible de faire apparaitre des monstres sur les deux dernieres lignes droites du chemin, mystère absolu
        //    if (y > 13*TailleCarre){
        //        Monstres bebe2 = new Bebe(10, 2, 1, x + 10, 14*TailleCarre);
        //        liste_monstres.ajouterEnFin(bebe2);
        //        Monstres bebe3 = new Bebe(10, 2, 1, x + 20, 14*TailleCarre);
        //        liste_monstres.ajouterEnFin(bebe3);
        //    }
        //    else{
        //        Monstres bebe2 = new Bebe(10, 2, 1, 17*TailleCarre, 15*TailleCarre - 8);
        //        liste_monstres.ajouterEnFin(bebe2);
        //        Monstres bebe3 = new Bebe(10, 2, 1, 17*TailleCarre, 15*TailleCarre);
        //        liste_monstres.ajouterEnFin(bebe3);
        //    }
        //}
        //else{
        //    if (x > 16*TailleCarre){
        //        Monstres bebe2 = new Bebe(10, 2, 1, 17*TailleCarre, y + 10);
        //        liste_monstres.ajouterEnFin(bebe2);
        //        Monstres bebe3 = new Bebe(10, 2, 1, 17*TailleCarre, y + 20);
        //        liste_monstres.ajouterEnFin(bebe3);
        //    }
        //    else{
        //        Monstres bebe2 = new Bebe(10, 2, 1, x + 10, y);
        //        liste_monstres.ajouterEnFin(bebe2);
        //        Monstres bebe3 = new Bebe(10, 2, 1, x + 20, y);
        //        liste_monstres.ajouterEnFin(bebe3);
        //    }
        //}
        }
        if (lvl == 2){
            Monstres bombe1 = new Bombe(10, 2, 1, x, y);
            liste_monstres.ajouterEnFin(bombe1);

            if (y == TailleCarre*2){
                Monstres bombe2 = new Bombe(10, 2, 1, x-10, y);
                liste_monstres.ajouterEnFin(bombe2);
                Monstres bombe3 = new Bombe(10, 2, 1, x-20, y);
                liste_monstres.ajouterEnFin(bombe3);
            }

            else if (x == TailleCarre*15){
                if (y < 4*TailleCarre){
                    Monstres bombe2 = new Bombe(10, 2, 1, x-10, 2*TailleCarre);
                    liste_monstres.ajouterEnFin(bombe2);
                    Monstres bombe3 = new Bombe(10, 2, 1, x-20, 2*TailleCarre);
                    liste_monstres.ajouterEnFin(bombe3);
                }
                else{
                    Monstres bombe2 = new Bombe(10, 2, 1, x, y-10);
                    liste_monstres.ajouterEnFin(bombe2);
                    Monstres bombe3 = new Bombe(10, 2, 1, x, y-20);
                    liste_monstres.ajouterEnFin(bombe3);
                }
            }

            else if (y == 11*TailleCarre){
                if (x < 17*TailleCarre){
                    Monstres bombe2 = new Bombe(10, 2, 1, 15*TailleCarre, y - 10);
                    liste_monstres.ajouterEnFin(bombe2);
                    Monstres bombe3 = new Bombe(10, 2, 1, 15*TailleCarre, y - 20);
                    liste_monstres.ajouterEnFin(bombe3);
                }
                else{
                    Monstres bombe2 = new Bombe(10, 2, 1, x - 10, y);
                    liste_monstres.ajouterEnFin(bombe2);
                    Monstres bombe3 = new Bombe(10, 2, 1, x - 20, y);
                    liste_monstres.ajouterEnFin(bombe3);
                }
            }
        }

    }
}
