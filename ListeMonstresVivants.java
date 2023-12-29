import java.util.LinkedList;
import java.util.ListIterator;

public class ListeMonstresVivants {
    private LinkedList<Monstres> liste;


    public ListeMonstresVivants() {
        liste = new LinkedList<>();
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
        return liste.getFirst();
    }
    public boolean isEmpty() {
        return premier() == null; //liste vide ?
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