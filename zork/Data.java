public class Data {
    protected String nom;
    protected int poids;
    protected boolean etat;

    public Data(){

    }
    public Data(String nom, int poids, boolean etat) {
        this.nom = nom;
        this.poids = poids;
        this.etat = etat;
    }

    public String getNom() {
        return nom;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public boolean getEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

}
