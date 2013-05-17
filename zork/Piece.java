   
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 *  Une piece dans un jeu d'aventure. <p>
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p> <p>
 *
 *  Une "Piece" represente un des lieux dans lesquels se d√©roule l'action du
 *  jeu. Elle est reli√©e a au plus quatre autres "Piece" par des sorties. Les
 *  sorties sont √©tiquett√©es "nord", "est", "sud", "ouest". Pour chaque
 *  direction, la "Piece" poss√®de une r√©f√©rence sur la piece voisine ou null
 *  s'il n'y a pas de sortie dans cette direction.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.1
 * @since      August 2000
 */

public class Piece {
	private String description;
/*Aidara Modification*/
  private int nbobjet;
 	private List <Objectpiece> mesobjets;
 	private List<Animer> vivant;
	/*AIDARA FIN*/
	// m√©morise les sorties de cette piece.
	private Map sorties;
	/**
	 *  Initialise une piece d√©crite par la chaine de caract√®res sp√©cifi√©e.
	 *  Initialement, cette piece ne poss√®de aucune sortie. La description fournie
	 *  est une courte phrase comme "la biblioth√®que" ou "la salle de TP".
	 *
	 * @param  description  Description de la piece.
	 */
	public Piece(String description) {
	    mesobjets=new ArrayList<Objectpiece>();
	    vivant=new ArrayList<Animer>();
		this.description = description;
		sorties = new HashMap();
		nbobjet=0;
	}


	/**
	 *  D√©finie les sorties de cette piece. A chaque direction correspond ou bien
	 *  une piece ou bien la valeur null signifiant qu'il n'y a pas de sortie dans
	 *  cette direction.
	 *
	 * @param  nord   La sortie nord
	 * @param  est    La sortie est
	 * @param  sud    La sortie sud
	 * @param  ouest  La sortie ouest
	 */
	public void setSorties(Piece nord, Piece est, Piece sud, Piece ouest) {
		if (nord != null) {
			sorties.put("nord", nord);
		}
		if (est != null) {
			sorties.put("est", est);
		}
		if (sud != null) {
			sorties.put("sud", sud);
		}
		if (ouest != null) {
			sorties.put("ouest", ouest);
		}
	}


	/**
	 *  Renvoie la description de cette piece (i.e. la description sp√©cifi√©e lors
	 *  de la cr√©ation de cette instance).
	 *
	 * @return    Description de cette piece
	 */
	public String descriptionCourte() {
		return description;
	}


	/**
	 *  Renvoie une description de cette piece mentionant ses sorties et
	 *  directement format√©e pour affichage, de la forme: <pre>
	 *  Vous etes dans la biblioth√®que.
	 *  Sorties: nord ouest</pre> Cette description utilise la chaine de caract√®res
	 *  renvoy√©e par la m√©thode descriptionSorties pour d√©crire les sorties de
	 *  cette piece.
	 *
	 * @return    Description affichable de cette piece
	 */
	public String descriptionLongue() {
		return "Vous etes dans " + description + ".\n" + descriptionSorties();
	}


	/**
	 *  Renvoie une description des sorties de cette piece, de la forme: <pre>
	 *  Sorties: nord ouest</pre> Cette description est utilis√©e dans la
	 *  description longue d'une piece.
	 *
	 * @return    Une description des sorties de cette pi√®ce.
	 */
	public String descriptionSorties() {
		String resulString = "Sorties:";
		Set keys = sorties.keySet();
		for (Iterator iter = keys.iterator(); iter.hasNext(); ) {
			resulString += " " + iter.next();
		}
		return resulString;
	}
  

	/**
	 *  Renvoie la piece atteinte lorsque l'on se d√©place a partir de cette piece
	 *  dans la direction sp√©cifi√©e. Si cette piece ne poss√®de aucune sortie dans cette direction,
	 *  renvoie null.
	 *
	 * @param  direction  La direction dans laquelle on souhaite se d√©placer
	 * @return            Piece atteinte lorsque l'on se d√©place dans la direction
	 *      sp√©cifi√©e ou null.
	 */
	public Piece pieceSuivante(String direction) {
		return (Piece) sorties.get(direction);
	}
	//AIDARA modification
	/**
	 * la mÈthode ajoudobjet prends en argument un objet et ajoute cet objet ‡ la piËce courante
	 *@param obj c'est l'objet ‡ ajouter ‡ la piËce
	 *@author aidara
	 */
	public void ajoudobjet(Objectpiece obj)
	{
		this.mesobjets.add(obj);
		nbobjet++;
	}
	public void ajouAnimer(Animer obj)
	{
		this.vivant.add(obj);
		nbobjet++;
	}
	/**
	 * la mÈthode affichemesobjet prends en arguments une piËce et affiche ces objets.
	 * @author aidara
	 */
	public void affichemesobjet()
	{
		int i=0;
		if(!mesobjets.isEmpty())
		{
		    System.out.println("******************************************************************************");
			System.out.println("    IL y'a "+ nbobjets() +" objets dans la piËce  "+ descriptionCourte()+" qui sont:");
            System.out.println("*******************************************************************************");
            //for(int i=0;i<mesobjets.size()-1;i++)
            for(Objectpiece ob:mesobjets)
           {	
        		    System.out.println("Nom de l'object: "+mesobjets.get(i).getnomobjet()+ "               poids: "+mesobjets.get(i).getpoids()+" KGS");
        		  System.out.println("");
        		  i++;
        		}
       }
       else
       {
        System.out.println("IL N'Y A PAS D'OBJET DANS CETTE PIECE");
       }
	}
	/**
	 * la mÈthode rechercheobjet prend en argument  le nom d'un objet , elle renvoie
	 * l'indice de l'objet s'il existe dans cette piËce.sinon renvoie -1;
	 * @param nom c'est le nom de lobjet a cherchÈ dans la piËce.
	 * @return  l'indice de l'objet si l'objet est trouvÈ dans la piËce sinon -1;
	 * @author aidara
	 */
	public int rechercheobjet(String nom)
	{ int i=0;
	    
	    
		if(!this.mesobjets.isEmpty())
		{
			for(Objectpiece ob:this.mesobjets)
			{	if(this.mesobjets.get(i).getnomobjet().equalsIgnoreCase(nom))
				{	if(this.mesobjets.get(i).getetat()==true)
				     return i;
					else
					{
						System.out.println("DÈsolÈ cet objet est non emportable !");
						return -1;
					}
				}
				i++;
            }
			System.out.println("DÈsolÈ cet objet n'existe pas dans la piËce !");
		}
		    return -1;
		
	}
	/**
	 * la mÈthode renvoieobjet prends en argument un indice, elle renvoie l'objet situÈ
	 * ‡ cet indice;
	 * @param i c'est l'indice de l'objet ‡ extraire;
	 * @return elle renvoie un objectpiece 
	 * @author aidara
	 */
	public Objectpiece renvoieobjet(int i)
	{    
		return this.mesobjets.get(i);
	}
	/**
	 * la mÈthode supprimerobjet prends en arguments  un indice,elle supprime un objet de la 
	 * piËce d'indice i. Cette mÈthode est appeÈ lorsque un joueur transporte un objet d'une piËce.
	 * @param i  c'est l'indice de l'objet ‡ supprimer dans la piËce.
	 * @author aidara
	 */
	public void supprimerobjet(int i)
	{
		this.mesobjets.remove(i);
		nbobjet--;
	}
	/**
	 * renvoie le nombre d'objet
	 * @return renvoie le nombre d'objet de la piËce.
	 */
	public int nbobjets()
	{
	   return nbobjet;
   }
   /**
    * renvoie le poids d'un objet dont l'indice est i
    * @param i c'est l'indice de l'objet dont la mÈthode doit retourner le poids.
    */
   public int renvoiepoids(int i)
   {
    return this.mesobjets.get(i).getpoids(); 
   }
   /**
    * renvoie true si lobjet dont l'indice passÈ en argument est transportable
    * @param i c'est l'indice de l'objet dont sa transportabilitÈ doit etre verifiÈ.
    * @return true si l'objet d'indice i est transportable false sinon.
    */
   public boolean transportable(int i)
   {
     if(!mesobjets.isEmpty())
       {    
            
                   if(mesobjets.get(i).getetat()==true)
                       return true;
            
       }
     return false;
    }
    /**  PARTIE 2 */
    public boolean RechercheSupprime(String nom)
    {  
        int i=0;
          if(!this.vivant.isEmpty())
		{
			for(Animer ob:this.vivant)
			{	if(this.vivant.get(i).getNom().equalsIgnoreCase(nom))
				{	
				    this.vivant.remove(i);
				    return true;
				}
				i++;
            }
			
		}
		    return false;
        
    }
}

