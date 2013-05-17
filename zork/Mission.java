import java.util.List;
import java.util.ArrayList;
import java.util.Random;
/**
 * <p> Une mission dans un jeu d'aventure </p>
 * <p> cette classe fait partie du logiciel Zork. Un jeu d'aventure simple en mode texte.</p>
 * 
 * <p> la mission consite a choisir al�atoirement les objets transportable dans les pi�ces et faire la somme 
 * de leurs poids qui sera le poids max a atteindre, fix� le nombre de pi�ces a visit� par le joueur.
 * <ul>
 *     <li> les pi�ces c'est l'enssemble des pi�ces du jeu</li>
 *     <li> le poids de victoire est le poids max que le joueur doit atteindre</li>
 *     <li> visite c'est le nombre de pi�ce au moins a visit�</li>
 *     
 *  </ul>
 *  </p>
 *  
 *  @invariant getvisite() >= 0;
 *  @invariant poidsdevictoire() >= 0;
 *  
 *  
 * 
 * @author (AIDARA moussa) 
 * @version (a version number or a date)
 * 
 
 */
public class Mission
{
    private List<Piece> lespieces;
    private int poidsdevictoire=0;
    private int visite=0;

   /**
     * Constructor for objects of class Mission
     * @requires p != null;
     * @ensures getvisite() == 0;
     * @ensures poidsdevictoire() == 0;
     */
    
     public Mission(List<Piece> p)
     {   int i=0;
         lespieces=new ArrayList<Piece>();
         for(i=0;i<p.size();i++)
         lespieces.add(p.get(i));
     }
     
      /**
       * d�termination de la mission elle consite � choisir au hasard un nombre de pi�ce, dont la sommes du poids des objets transportables sera le poids 
       * max autoris�. alors le joueur doit transport� des objets de tel sorte que le poids total des objets transport� soit �gale a celui fournit par la mission.
       * @ensures getvisite() > 0;
       * @ensures poidsdevictoire() > 0;
       */
    public void mission()
    {
        int d=3;int i=0; int var=0,cpt=0,cpt2=0;
        int f=lespieces.size()-1;
        int nombrealeatoire;
      Random rand;
      rand= new Random();
       
       nombrealeatoire =aleatoire(d,f,rand); 
       var=nombrealeatoire;
       //System.out.println(" le nombre aleatoire est "+ var);
	   for(i=0;i<=var;i++)
	   {   rand= new Random();
	       if(lespieces.get(i).nbobjets()>0)
	       {
	           nombrealeatoire =rand.nextInt(lespieces.get(i).nbobjets()-1); 
	           
	           while((!lespieces.get(i).transportable(nombrealeatoire) && cpt2 < 3 ) )
	           {
	               rand= new Random();
	                nombrealeatoire =rand.nextInt(lespieces.get(i).nbobjets()-1);
	                cpt2++;
	           }
	          if(cpt2 < 3)  
	          {
	               cpt++; cpt2=0;
	               poidsdevictoire=poidsdevictoire+lespieces.get(i).renvoiepoids(nombrealeatoire);
	               //System.out.println("i vaut " + i +" le poids vaut "+  lespieces.get(i).renvoiepoids(nombrealeatoire));
	          }
	       }
	   }
	   
	   visite=cpt;
	    affichage();
    }
    /**
     * renvoie le poidsdevictoire
     * @return renvoie le poids max a atteindre par le joueur
     * @ensures poidsdevictoire() > 0;
     */
    public int getpoidsdevictoire()
    {
     return  poidsdevictoire;
    }
    /**
     * renvoie le nombre de pi�ce a visit�
     * @return renvoie le nombre de pi�ce au moins a visit� par le joueur 
     * @ensures getvisite() > 0;
     */
    public int getvisite()
    {
     return  visite;
    }
    /*
     * affichage de la mission
     */
    public void affichage()
    {
        System.out.println("****************************************************");
        System.out.println("*                  VOTRE MISSION                   *");
        System.out.println("****************************************************");
        System.out.println("        visit� au moins "+ visite + " pi�ces");
	    System.out.println("     Transporter exactement "+ visite + " objets");
	    System.out.println("    dont le poids totale sera "+ poidsdevictoire + " KGS");
	    System.out.println("****************************************************");
    
    }
    /**
     * gen�re un nombre aleatoire entre debut et fin
     * @param d  debut 
     * @param f  fin 
     * @param r  c'est le random
     * @return le nombre compris entre @param d et @param f
     */
    public int aleatoire(int d , int f,Random r)
    {
      long range = (long)f - (long)d + 1;
    // compute a fraction of the range, 0 <= frac < range
      long fraction = (long)(range * r.nextDouble());
      return (int)(fraction + d); 
    }
    /**
     * V�rifie si le joueur a gagn�
     * @param j c'est le joueur
     * 
     */
   public boolean gagner(joueur j)
   {
     if(j.nombreobjet()== visite && j.getnbpiecevisite()>=visite && j.getpoids()==poidsdevictoire )
       return true;
     return false;
    }
    /**
     * affichage du plan 
     */
    
    public void plan()
    {
     System.out.println("************************************************************************************");
     System.out.println("*                              Affichage du plan                                   *");
     System.out.println("************************************************************************************");
     int i=0;Piece p;
     for(Piece pi:lespieces)
     {
        System.out.println(" Nom de la piece: "+lespieces.get(i).descriptionCourte());
        p=lespieces.get(i).pieceSuivante("nord");
        if(p!=null)
         System.out.println(" Au nord:  "+p.descriptionCourte());
         p=lespieces.get(i).pieceSuivante("sud");
        if(p!=null)
         System.out.println(" Au sud:  "+p.descriptionCourte());
         p=lespieces.get(i).pieceSuivante("est");
        if(p!=null)
         System.out.println(" A l'est:  "+p.descriptionCourte());
         p=lespieces.get(i).pieceSuivante("ouest");
        if(p!=null)
         System.out.println(" A l'ouest:   "+p.descriptionCourte());
         System.out.println("");
       i++;
     }
     
     
    }
}

