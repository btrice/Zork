

/**
 * <p> Un Objectpiece  est une classe permertant de crée des objets .</p>
 *  <p>Cette classe fait partie du logiciel Zork un jeu d'aventure simple en mode texte </p>
 *  
 *   <p> un Objectpiece est caracterisé par :
 *   <ul>
 *        <li> le nomobjet qui designe le nom de l'objets </li>
 *        <li> son poids </li>
 *        <li> son etat qui designe sa transportabilité </li>
 *   </ul>
 *@invariant getnomobjet() != null;
 *@invariant getpoids() > 0;
 *@invariant (getetat()==false || getetat()==true);
 * @author aidara
 *
 */
public class Objectpiece {
	private String nomobjet;
	private int poids;
	private boolean etat;

	/**
	 * constructeur de la classe Objectpiece qui prend en argument le nom de l'objet, son poids , et son etat
	 * @param nom le nom de l'objet
	 * @param p   le poids de l'objet
	 * @param et  l'etat de l'objet true ou false
	 */
	public Objectpiece(String nom,int p,boolean et) {
		this.nomobjet=nom;
		this.poids=p;
		this.etat=et;
	}
	/**
	 * la methode getnomobjet renvoie le nom de l'objet .
	 * @return le nom de l'objet
	 */
	public String getnomobjet()
	{
		return this.nomobjet;
	}
	/**
	 * la méthode getpoids renvioe le poids de l'objet.
	 * @return le poids de l'objet
	 */
	public int getpoids()
	{
		return this.poids;
	}
	/**
	 * la méthode getetat renvoie l'etat de l'objet true pour emportable et false pour non emportable
	 * @return true pour emportable et false pour non emportable
	 */
	public boolean getetat()
	{
		return this.etat;
	}
    /**
     * compare deux objet zork
     * @param obj c'est le nom de l'objets a comparé avec l'objet courant
     */
	public boolean equals(Object obj)
	{
	    if(!(obj instanceof Objectpiece))
	    {
	       return false;
	    }
	    Objectpiece ob=(Objectpiece) obj;
	    if(getetat()!= ob.getetat() && getpoids()!=ob.getpoids() && getnomobjet()!=ob.getnomobjet())
	     {
	         return false;
	     }
	     else
	     {
	       return true;
	     }
	  }  
	
}
