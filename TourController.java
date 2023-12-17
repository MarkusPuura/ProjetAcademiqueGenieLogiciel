import java.util.ArrayList;

public class TourController {
    private ArrayList<Tours1> towersList;

    public  TourController(){
        towersList = new ArrayList<>();

    }
    public void addTower(Tours1 tower) {
        towersList.add(tower);
    }

    public ArrayList<Tours1> getTowersList() {
        return towersList;
    }

    public void setTowersList(ArrayList<Tours1> towersList) {
        this.towersList = towersList;
    }
}
