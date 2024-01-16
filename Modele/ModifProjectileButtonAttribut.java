package Modele;

public class ModifProjectileButtonAttribut {
    private int ameliorationCout;
    private int niveauAmelioration;
    private int venteArgentGagne;

    public ModifProjectileButtonAttribut(Projectile p) {
        this.niveauAmelioration = 0;
        this.ameliorationCout = p.getPrice() + 30;
        this.venteArgentGagne = p.getPrice() - 30;

    }

    public int getNiveauAmelioration() {
        return this.niveauAmelioration;
    }

    public boolean niveauxInf3() {
        return this.niveauAmelioration <= 3;
    }

    public void setAmeliorationCout() {
        if (niveauxInf3()) {
            this.ameliorationCout += 30;
            this.venteArgentGagne += 30;
            this.niveauAmelioration++;
        }
    }

    public void setVenteArgentGagne() {

        this.venteArgentGagne += 30;

    }

    public int getAmeliorationCout() {
        return this.ameliorationCout;
    }

    public int getVenteArgentGagne() {
        return this.venteArgentGagne;
    }

    public void setAmelioration(Projectile p) {
        if (niveauxInf3()) {
            p.amelioration(this.niveauAmelioration);
        }
    }
}
