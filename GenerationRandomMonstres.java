public class GenerationRandomMonstres {
    int niveau;

    public GenerationRandomMonstres(int niveau){
        this.niveau = niveau;
    }

    public void generermonstre(Chrono chrono, int TailleCarre, ListeMonstresVivants liste_monstres, int debut){
        long temps = System.nanoTime();

        if (debut == 0){
            Monstres zombie = new Zombie(20, 1, 5, 0, TailleCarre*2);
            liste_monstres.ajouterEnFin(zombie);
        }
        

        if (chrono.min < 5){
            if (temps%(25000/niveau) == 0){
                Monstres zombie1 = new Zombie(20, 1, 5, 0, TailleCarre*2);      //Attention: la vitess peut que être 
                liste_monstres.ajouterEnFin(zombie1);                                             // = à 1; 2; 4; 8; 16; 0.5; 0.25
            }
            if (temps%(40000/niveau) == 0){
                Monstres serpent1 = new Serpent(15, 2, 3, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(serpent1);
            }
        }
        if (chrono.min < 4){
            if (temps%(30000/niveau) == 0){
                Monstres zombie1 = new Zombie(20, 1, 5, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(zombie1);
            }
            if (temps%(50000/niveau) == 0){
                Monstres serpent1 = new Serpent(15, 2, 3, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(serpent1);
            }
        }
        if (chrono.min < 3){
            if (temps%(30000/niveau) == 0){
                Monstres zombie1 = new Zombie(20, 1, 5, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(zombie1);
            }
            if (temps%(40000/niveau) == 0){
                Monstres serpent1 = new Serpent(15, 2, 3, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(serpent1);
            }
        }
        if (chrono.min < 2){
            if (temps%(20000/niveau) == 0){
                Monstres zombie1 = new Zombie(20, 1, 5, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(zombie1);
            }
            if (temps%(40000/niveau) == 0){
                Monstres serpent1 = new Serpent(15, 2, 3, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(serpent1);
            }
        }
        if (chrono.min < 1){
            if (temps%(20000/niveau) == 0){
                Monstres zombie1 = new Zombie(20, 1, 5, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(zombie1);
            }
            if (temps%(30000/niveau) == 0){
                Monstres serpent1 = new Serpent(15, 2, 3, 0, TailleCarre*2);
                liste_monstres.ajouterEnFin(serpent1);
            }
        }
    }
}
