public class MonObjet extends Data{

    public MonObjet(String nom, int poids, boolean etat) {
        this.nom = nom;
        this.poids = poids;
        this.etat = etat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonObjet monObjet = (MonObjet) o;

        if (getPoids() != monObjet.getPoids()) return false;
        if (getEtat() != monObjet.getEtat()) return false;
        return getNom() != null ? getNom().equals(monObjet.getNom()) : monObjet.getNom() == null;
    }

    @Override
    public int hashCode() {
        int result = getNom() != null ? getNom().hashCode() : 0;
        result = 31 * result + getPoids();
        result = 31 * result + (getEtat() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MonObjet{" +
                "nom='" + nom + '\'' +
                ", poids=" + poids +
                ", etat=" + etat +
                '}';
    }
}
