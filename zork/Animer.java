import java.util.Random;

public class Animer extends Data {
    private final static String direction [] = {"nord", "sud", "est", "ouest"};
    public Animer(String nom, int poids, boolean etat) {
        this.nom = nom;
        this.poids = poids;
        this.etat = etat;
    }

    public Commande deplacerAleatoire(){
        return new Commande("Aller",direction[new Random().nextInt(direction.length)]);
    }
}
