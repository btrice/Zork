import java.util.List;
import java.util.ArrayList;
/**
 * <p> Un joueur dans un jeu d'aventure .</p>
 *  <p>Cette classe fait partie du logiciel Zork un jeu d'aventure simple en mode texte </p>
 *  
 *   <p> Un joueur peu transporté des objets avec lui. chaque objet a un poids.
 *       Le joueur ne peut transporter des objets dans la mesure ou le poids total
 *       des objets transportés ne dépasse pas le poids max fixé par la Classe mission .
 *       Un joueur est caracterisé par :
 *       <ul>
 *            <li> son nom </li>
 *            <li> son score </li>
 *            <li> le poids total des objets transporté </li>
 *            <li> le poids max à atteint fixé par la mission</li>
 *            <li> le nombre d'objet transporté </li>
 *            <li> le nombre de pièce visité par le joueur</li>
 *            <li> les objets qu'il transporte</li>
 *       </ul>
 *   </p>
 * @invariant getnom() != null;
 * @invariant ( getscore() && setscore() ) >= 0;
 * @invariant getpoids() >= 0 && getpoids() <= getpoidsmax();
 * @invariant setpoidsmax() >= 0;
 * @invariant  nombreobjet() >= 0;
 * @invariant setnbpiecevisite() >= 0;
 */

public class joueur {
    private String nom;
	private int score;
	private int poids;
	private int poidsmax;
	private int nbobjet;
	private int nbpiecevisite;
	private List<Objectpiece> objetemporter;
	/**
	 * @requires nom != null;
	 * @ensure getscore() == 0;
	 * @ensure  nombreobjet() == 0;
	 * @ensure  getpoids() ==0;
	 */
	public joueur(String nom) {
		this.nom=nom;
		objetemporter=new ArrayList<Objectpiece>();
		score=0;
		nbobjet=0;
	}
	/**
	 * renvoie le nom du joueur
	 */
	public String getnom()
	{
	    return this.nom;
	}
	/**
	 * initialisation du poidsmax
	 * @param p c'est le poids max à ne pas depassé
	 */
	public void setpoidsmax(int p)
	{
	   poidsmax=p;
	 }
	/**
	 * renvoie le score du joueur
	 * @return renvoie le score du joueur
	 */
	public int getscore()
	{
	    return score;
	}
	/**
	 * incrémente le score de plus 10 
	 * 
	 */
	public void setscore()
	{
	     score=score+10;
	}
	/**
	 * incrémente le nombre piece visité de plus 1
	 */
	public void setnbpiecevisite()
	{
	     nbpiecevisite=nbpiecevisite+1;
	}
	/**
	 * renvoie le nombre de piece visité
	 * @return  renvoie le nombre de pièce visité par le joueur.
	 */
	public int getnbpiecevisite()
	{
	    return  nbpiecevisite;
	}
	/**
	 * renvoie le poids total des objets transportés
	 * @return renvoie le poids total des objets transportés par le joueurs.
	 */
	public int getpoids()
	{
	    return poids;
	}
	/**
	 * renvoie le poids max fixé
	 * @return renvoie le poids max .
	 */
	public int getpoidsmax()
	{
	    return poidsmax;
	}
	/**
	 * Permet au joueur de transporter un objet
	 * @return true si l'objet a été transporté false sinon.
	 * @requires obj != null;
	 * @requires obj.esttransporte(obj.getnom());
	 * @ensures == \all(nombreobjet()) + 1;
	 */
    public boolean transporter(Objectpiece obj)
    {  
        int p=poids+obj.getpoids();
    	if(p<=poidsmax)
        { objetemporter.add(obj);
    	  nbobjet++;
    	  poids=p;
    	  setscore();
    	  return true;
        }
        else
        {
        System.out.println("La somme du poids de vos objets déja tranportés est "+poids+" et celui du nouveau objets est "+obj.getpoids());
        System.out.println(" et "+poids+" + "+obj.getpoids()+" est superieur au poids max  autorisés");
        return false;
        }
    }
    /**
     * deposer un objet emporter mais renvoie l'objet pour le deposer dans la pièce courante
     * @param nom c'est le nom de l'objet
     */
    public Objectpiece  deposer(String nom)
    {
         int i=0; 
         
            for(Objectpiece ob: objetemporter)
            {
                if(objetemporter.get(i).getnomobjet().equalsIgnoreCase(nom))
                {
                  ob= objetemporter.get(i);
                  nbobjet--;
                  score=score-10;
                  poids=poids-objetemporter.get(i).getpoids();
                    objetemporter.remove(i);
                  return ob;
                }
            i++;
            }
         return null;   	
    	
    }
    /*
     * affichage des objets tranporter par le joueur
     */
   public void affichagedesobjets()
   { int i=0;
       if(!objetemporter.isEmpty())
       {   
           System.out.println("        *********************************************");
           System.out.println("                  Mes objets transportés sont        ");
           System.out.println("        *********************************************");
           System.out.println("               objets         |         poids        ");
           System.out.println("        ---------------------------------------------");
            for(Objectpiece ob:objetemporter)
           {
             System.out.println("                 "+this.objetemporter.get(i).getnomobjet()+"                  "+this.objetemporter.get(i).getpoids()+"KGS");
	        i++;
           }
            System.out.println("        *********************************************");
        }
        else
        {
         System.out.println("Vous avez pas transporter d'objet d'abord :");
        }
    
   }
   /*
    * informations sur le joueur
    */
   public void informations(joueur j)
   {
     System.out.println("        ---------------------------------------------");
     System.out.println("        | Je m'appelle :    "+    j.getnom());
     System.out.println("        ---------------------------------------------");
     System.out.println("        | Mon score est :   "+     j.getscore());
     System.out.println("        ---------------------------------------------");
     System.out.println("        | Le poids total:    "+     j.getpoids()+"KGS");
     System.out.println("        ---------------------------------------------");
     System.out.println("        | nombre d'objets:    "+     j.nombreobjet());
       System.out.println("        ---------------------------------------------");
   }
   /*
    * renvoie le nombre d'objet transporter par le joueur
    * @return le nombre d'objet transporté par le joueur
    */
    public int nombreobjet()
   {
    return nbobjet;
    }
   /*
    * renvoie true si le joueur transporte l'objet dont le nom est donnée en argument
    */
    public boolean esttransporte(String nom)
    { int i=0;
       for(Objectpiece ob:objetemporter)
       {
         if(objetemporter.get(i).getnomobjet().contains(nom))
            return true;
         i++;
        }
       return false;
    }
  /* public static void main (String args[]) {
		joueur joueur1;
		joueur1=new joueur("aidara");
		 /*Objectpiece obje,obj2,obj3;
		obje= new Objectpiece("table",5,false);
		obj2= new Objectpiece("bic",2,true);
		obj3= new Objectpiece("chaisse",15,true);
		joueur1.emporter(obje);
		joueur1.emporter(obj2);
		joueur1.emporter(obj3);
			joueur1.affichage();
		joueur1.retirer(obj2);
			joueur1.affichage();
			//System.out.println("Ajout effectuer avec succes");
	
    }*/
}
