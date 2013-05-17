
/**
 * Abstract class EtreAnime - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class EtreAnime
{
    // instance variables - replace the example below with your own
    private String nom;
    private int poids;

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y    a sample parameter for a method
     * @return        the sum of x and y 
     */
    public EtreAnime(int poids,String nom){
     this.poids=poids;
     this.nom=nom;
    }
    public Commande deplacerAleatoire()
    {
        
        return new Commande("aller","nord");
    }
}
