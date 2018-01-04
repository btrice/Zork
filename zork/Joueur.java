import java.util.Iterator;

public class Joueur extends Stockage<MonObjet> {
    private String nom;
    private int poids;
    private int poidsMax;
    private int score;
    private int nbpiecevisite;

    public Joueur(String nom, int poids) {
        this.nom = nom;
        this.poids = poids;
        this.score = 0;
        this.nbpiecevisite = 0;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNbpiecevisite() {
        return nbpiecevisite;
    }

    public void setNbpiecevisite(int nbpiecevisite) {
        this.nbpiecevisite = nbpiecevisite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPoidsMax() {
        return poidsMax;
    }

    public void setPoidsMax(int poidsMax) {
        this.poidsMax = poidsMax;
    }

    public void incrementPieceVisite(){
        nbpiecevisite++;
    }

    public void incrementScore(){
        score++;
    }

    public void decrementScore(){
        score--;
    }

    public MonObjet rechercher(String nom){
        if (this.isEmpty()) {
            return null;
        }
        Iterator it = this.iterator();
        while (it.hasNext()){
            MonObjet o = (MonObjet) it.next();
            if (o.getNom().equalsIgnoreCase(nom)) {
                return o;
            }
        }
        return null;
    }

    public int getAllPoids(){
        if (this.isEmpty()) {
            return 0;
        }
        int p = 0;
        Iterator it = this.iterator();
        while (it.hasNext()){
            MonObjet o = (MonObjet) it.next();
            p = p + o.getPoids();
        }
        return p;
    }

    public void afficher(){

        System.out.println("        *********************************************");
        System.out.println("                  Mes objets transportés sont        ");
        System.out.println("        *********************************************");
        System.out.println("               objets         |         poids        ");
        System.out.println("        ---------------------------------------------");
        if(!this.isEmpty()) {
            Iterator it = this.iterator();
            while (it.hasNext()) {
                MonObjet o = (MonObjet) it.next();
                System.out.println("           " + o.getNom() + "       " + o.getPoids());
                System.out.println("        *********************************************");
            }
        } else {
            System.out.println("          Aucun objet n'a été transporté            ");
        }
    }

    public void informations(){
        System.out.println("        ---------------------------------------------");
        System.out.println("        | Je m'appelle         :   "+ getNom());
        System.out.println("        ---------------------------------------------");
        System.out.println("        | Mon Poids            :   "+ getPoids());
        System.out.println("        ---------------------------------------------");
        System.out.println("        | Mon score est        :   "+ getScore());
        System.out.println("        ---------------------------------------------");
        System.out.println("        | Le poids des objets  :   "+ getAllPoids()+" KGS");
        System.out.println("        ---------------------------------------------");
        System.out.println("        | Nombre d'objets      :   "+  getTotal());
        System.out.println("        ---------------------------------------------");
    }

    public boolean transporter(MonObjet ob){
        int p = getAllPoids() + ob.getPoids();
        if( p <= poidsMax){
            this.add(ob);
            incrementScore();
            return true;
        } else {
            System.out.println("La somme totale de votre poids et du poids de vos objets déja tranportés est :"+poids);
            System.out.println("Le nouveau objets pèse "+ob.getPoids());
            System.out.println("  "+poids+" + "+ob.getPoids()+" est superieur au poids max ("+ poidsMax +")  autorisés");
            return false;
        }
    }

    public void deposer(MonObjet ob){
        this.remove(ob);
        decrementScore();

    }


}
