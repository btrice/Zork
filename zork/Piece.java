   
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
 *  Une "Piece" represente un des lieux dans lesquels se déroule l'action du
 *  jeu. Elle est reliée a au plus quatre autres "Piece" par des sorties. Les
 *  sorties sont étiquettées "nord", "est", "sud", "ouest". Pour chaque
 *  direction, la "Piece" possède une référence sur la piece voisine ou null
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
	// mémorise les sorties de cette piece.
	private Map sorties;
	/**
	 *  Initialise une piece décrite par la chaine de caractères spécifiée.
	 *  Initialement, cette piece ne possède aucune sortie. La description fournie
	 *  est une courte phrase comme "la bibliothèque" ou "la salle de TP".
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
	 *  Définie les sorties de cette piece. A chaque direction correspond ou bien
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
	 *  Renvoie la description de cette piece (i.e. la description spécifiée lors
	 *  de la création de cette instance).
	 *
	 * @return    Description de cette piece
	 */
	public String descriptionCourte() {
		return description;
	}


	/**
	 *  Renvoie une description de cette piece mentionant ses sorties et
	 *  directement formatée pour affichage, de la forme: <pre>
	 *  Vous etes dans la bibliothèque.
	 *  Sorties: nord ouest</pre> Cette description utilise la chaine de caractères
	 *  renvoyée par la méthode descriptionSorties pour décrire les sorties de
	 *  cette piece.
	 *
	 * @return    Description affichable de cette piece
	 */
	public String descriptionLongue() {
		return "Vous etes dans " + description + ".\n" + descriptionSorties();
	}


	/**
	 *  Renvoie une description des sorties de cette piece, de la forme: <pre>
	 *  Sorties: nord ouest</pre> Cette description est utilisée dans la
	 *  description longue d'une piece.
	 *
	 * @return    Une description des sorties de cette pièce.
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
	 *  Renvoie la piece atteinte lorsque l'on se déplace a partir de cette piece
	 *  dans la direction spécifiée. Si cette piece ne possède aucune sortie dans cette direction,
	 *  renvoie null.
	 *
	 * @param  direction  La direction dans laquelle on souhaite se déplacer
	 * @return            Piece atteinte lorsque l'on se déplace dans la direction
	 *      spécifiée ou null.
	 */
	public Piece pieceSuivante(String direction) {
		return (Piece) sorties.get(direction);
	}
	//AIDARA modification
	/**
	 * la m�thode ajoudobjet prends en argument un objet et ajoute cet objet � la pi�ce courante
	 *@param obj c'est l'objet � ajouter � la pi�ce
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
	 * la m�thode affichemesobjet prends en arguments une pi�ce et affiche ces objets.
	 * @author aidara
	 */
	public void affichemesobjet()
	{
		int i=0;
		if(!mesobjets.isEmpty())
		{
		    System.out.println("******************************************************************************");
			System.out.println("    IL y'a "+ nbobjets() +" objets dans la pi�ce  "+ descriptionCourte()+" qui sont:");
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
	 * la m�thode rechercheobjet prend en argument  le nom d'un objet , elle renvoie
	 * l'indice de l'objet s'il existe dans cette pi�ce.sinon renvoie -1;
	 * @param nom c'est le nom de lobjet a cherch� dans la pi�ce.
	 * @return  l'indice de l'objet si l'objet est trouv� dans la pi�ce sinon -1;
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
						System.out.println("D�sol� cet objet est non emportable !");
						return -1;
					}
				}
				i++;
            }
			System.out.println("D�sol� cet objet n'existe pas dans la pi�ce !");
		}
		    return -1;
		
	}
	/**
	 * la m�thode renvoieobjet prends en argument un indice, elle renvoie l'objet situ�
	 * � cet indice;
	 * @param i c'est l'indice de l'objet � extraire;
	 * @return elle renvoie un objectpiece 
	 * @author aidara
	 */
	public Objectpiece renvoieobjet(int i)
	{    
		return this.mesobjets.get(i);
	}
	/**
	 * la m�thode supprimerobjet prends en arguments  un indice,elle supprime un objet de la 
	 * pi�ce d'indice i. Cette m�thode est appe� lorsque un joueur transporte un objet d'une pi�ce.
	 * @param i  c'est l'indice de l'objet � supprimer dans la pi�ce.
	 * @author aidara
	 */
	public void supprimerobjet(int i)
	{
		this.mesobjets.remove(i);
		nbobjet--;
	}
	/**
	 * renvoie le nombre d'objet
	 * @return renvoie le nombre d'objet de la pi�ce.
	 */
	public int nbobjets()
	{
	   return nbobjet;
   }
   /**
    * renvoie le poids d'un objet dont l'indice est i
    * @param i c'est l'indice de l'objet dont la m�thode doit retourner le poids.
    */
   public int renvoiepoids(int i)
   {
    return this.mesobjets.get(i).getpoids(); 
   }
   /**
    * renvoie true si lobjet dont l'indice pass� en argument est transportable
    * @param i c'est l'indice de l'objet dont sa transportabilit� doit etre verifi�.
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

