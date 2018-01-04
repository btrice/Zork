import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Mission extends Stockage<Piece> {
    private int poidsDeVictoire = 0;
    private int visite = 0;

    public Mission(List<Piece> p){
        this.addAll(p);
    }

    public int getPoidsDeVictoire() {
        return poidsDeVictoire;
    }

    public void setPoidsDeVictoire(int poidsDeVictoire) {
        this.poidsDeVictoire = poidsDeVictoire;
    }

    public int getVisite() {
        return visite;
    }

    public void setVisite(int visite) {
        this.visite = visite;
    }

    public void creerMission(){
        int debut = 3; // Pour avoir au moins 3 pièces pour créér la mission
        int fin = this.getTotal();
        int aleatoire;
        int nbEssaie = 0; //
        Random rand = new Random();
        int nbPieceVisite = aleatoire(debut, fin, rand);
        Iterator it = this.iterator();
        while ( it.hasNext() && nbPieceVisite > 0 ) {
            Piece p = (Piece) it.next();
            if (p.getTotal() > 0) {
                aleatoire = rand.nextInt(p.getTotal()); // l'index de l'objet
                while(!p.get(aleatoire).getEtat() && nbEssaie < 3) {
                    aleatoire = rand.nextInt(p.getTotal());
                    nbEssaie++;
                }
                if (nbEssaie < 3){
                    nbEssaie = 0;
                    visite++;

                    poidsDeVictoire = poidsDeVictoire + p.get(aleatoire).getPoids();
                }
            }
        }

    }

    public void afficher()
    {
        System.out.println("****************************************************");
        System.out.println("*                  VOTRE MISSION                   *");
        System.out.println("****************************************************");
        System.out.println("     visité au moins "+ visite + " pièces");
        System.out.println("     Transporter exactement "+ visite + " objets");
        System.out.println("     dont le poids totale sera "+ poidsDeVictoire + " KGS");
        System.out.println("****************************************************");

    }

    public int aleatoire(int d , int f, Random r)
    {
        long range = (long)f - (long)d + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * r.nextDouble());
        return (int)(fraction + d);
    }

    public void plan()
    {
        System.out.println("************************************************************************************");
        System.out.println("*                              Affichage du plan                                   *");
        System.out.println("************************************************************************************");
        Iterator it = this.iterator();
        Piece pp;
        while (it.hasNext()){
            Piece p = (Piece) it.next();
            System.out.println(" Nom de la piece: "+p.descriptionCourte());
            pp = p.pieceSuivante("nord");
            if(pp != null) {
                System.out.println(" Au nord:  " + p.descriptionCourte());
            }

            pp = p.pieceSuivante("sud");
            if(pp != null) {
                System.out.println(" Au sud:  " + p.descriptionCourte());
            }

            pp = p.pieceSuivante("est");
            if(pp != null) {
                System.out.println(" A l'est:  " + p.descriptionCourte());
            }

            pp = p.pieceSuivante("ouest");
            if(pp != null) {
                System.out.println(" A l'ouest:  " + p.descriptionCourte());
            }
        }
    }

    public boolean gagner(Joueur j){
        if(j.getTotal() == visite && j.getNbpiecevisite()>= visite && j.getPoids() == poidsDeVictoire){
            return true;
        }
        return false;
    }
}
