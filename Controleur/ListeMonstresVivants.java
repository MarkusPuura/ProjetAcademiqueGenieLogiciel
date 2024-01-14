package Controleur;

import java.util.LinkedList;
import java.util.ListIterator;

import Modele.Monstres;

public class ListeMonstresVivants {
    private LinkedList<Monstres> liste;

    public ListeMonstresVivants() {
        liste = new LinkedList<>();
    }

    public LinkedList<Monstres> getListe() {
        return this.liste;
    }

    public boolean estDansZone(Monstres premMonstre, Monstres monstreAChercher, int TailleCarre) {
        return Math.abs(premMonstre.getX() - monstreAChercher.getX()) <= 2 * TailleCarre
                && Math.abs(premMonstre.getY() - monstreAChercher.getY()) <= 2 * TailleCarre;
    }

    // Ajouter un objet en fin de liste
    public void ajouterEnFin(Monstres objet) {
        liste.addLast(objet);
    }

    // Supprimer un objet de la liste
    public void supprimer(Monstres objet) {
        liste.remove(objet);
    }

    // Accéder au premier élément de la liste
    public Monstres premier() {
        try {
            return liste.getFirst();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isEmpty() {
        return premier() == null; // liste vide ?
    }

    // Accéder à l'élément suivant dans la liste
    public Monstres suivant(Monstres objet) {
        ListIterator<Monstres> iterator = liste.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(objet) && iterator.hasNext()) {
                return iterator.next();
            }
        }
        return null;
    }

}
