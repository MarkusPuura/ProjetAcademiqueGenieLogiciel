public class GenerationRandomMonstres {
    int niveau;

    public GenerationRandomMonstres(int niveau){
        this.niveau = niveau;
    }

    public void generermonstre(Chrono chrono, int TailleCarre, ListeMonstresVivants liste_monstres, int debut){
        long temps = System.nanoTime();

        if (debut == 0){
            Monstres zombie = new Zombie(40, 1, 5, 0, TailleCarre*2);
            liste_monstres.ajouterEnFin(zombie);
        }
        

        if (chrono.min < 5){
            if (temps%(21000/niveau) == 0){
                Monstres zombie1 = new Zombie(40, 1, 5, 0, TailleCarre*2);      //Attention: la vitess peut que être 
                liste_monstres.ajouterEnFin(zombie1);                                             // = à 1; 2; 4; 8; 16; 0.5; 0.25
            }
            if (temps%(40000/niveau) == 0){
                Monstres serpent1 = new Serpent(20, 2, 3, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(serpent1);
            }
        }
        if (chrono.min < 4){
            if (temps%(31000/niveau) == 0){
                Monstres zombie1 = new Zombie(40, 1, 5, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(zombie1);
            }
            if (temps%(50000/niveau) == 0){
                Monstres serpent1 = new Serpent(20, 2, 3, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(serpent1);
            }
            if (temps%(80060/niveau) == 0){
                Monstres goleme1 = new Goleme(100, 1, 1, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(goleme1);
            }
        }
        if (chrono.min < 3){
            if (temps%(32060/niveau) == 0){
                Monstres zombie1 = new Zombie(40, 1, 5, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(zombie1);
            }
            if (temps%(40060/niveau) == 0){
                Monstres serpent1 = new Serpent(20, 2, 3, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(serpent1);
            }
            if (temps%(60000/niveau) == 0){
                Monstres goleme1 = new Goleme(100, 1, 1, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(goleme1);
            }
        }
        if (chrono.min < 2){
            if (temps%(21060/niveau) == 0){
                Monstres zombie1 = new Zombie(40, 1, 5, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(zombie1);
            }
            if (temps%(40120/niveau) == 0){
                Monstres serpent1 = new Serpent(20, 2, 3, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(serpent1);
            }
            if (temps%(50060/niveau) == 0){
                Monstres goleme1 = new Goleme(100, 1, 1, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(goleme1);
            }
        }
        if (chrono.min < 1){
            if (temps%(22120/niveau) == 0){
                Monstres zombie1 = new Zombie(40, 1, 5, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(zombie1);
            }
            if (temps%(30120/niveau) == 0){
                Monstres serpent1 = new Serpent(20, 2, 3, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(serpent1);
            }
            if (temps%(50120/niveau) == 0){
                Monstres goleme1 = new Goleme(100, 1, 1, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(goleme1);
            }
        }
    }
}
