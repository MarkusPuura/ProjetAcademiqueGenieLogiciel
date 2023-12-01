

public class Kama {         // si on rajoute pas d'autres propriétés en lien avec l'argent on pourra supprimer cette classe, juste 
    int portefeuille;       // faire un int portefeuille globale.

    public Kama(){
        this.portefeuille = 0;
    }

    void kamaUpdate(int taux){
        this.portefeuille += taux; 
    }
}
