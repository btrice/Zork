import java.util.StringTokenizer;

/**
 * Write a description of class Animer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Animer
{
    // instance variables - replace the example below with your own
    private String nom;

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y    a sample parameter for a method
     * @return        the sum of x and y 
     */
    public Animer(String nom){
     this.nom=nom;
    }
    
    public String getNom()
    {
      return this.nom;
    }
    public Commande deplacerAleatoire(Piece pc)
    {  
        String[] d=new String[5];
        String direction=pc.descriptionSorties();
        StringTokenizer tok = new StringTokenizer(direction);
        
      int i=0,cpt=0;
		while(tok.hasMoreTokens()) 
		{
		     d[i]=tok.nextToken();
		    i++;
		}	
		for(i=1;i<5;i++)
		  if(!(d[i].equals("")))
		    cpt++;
		    
		if(cpt==0)
		{ 
		   return new Commande("retour","");
		}
		else
		{
		    
           return new Commande("aller","nord");
        }
    }
    public void deplacer(Piece pc,Piece ps)
    {  
       
       if(pc.RechercheSupprime(this.getNom()))
       {
            ps.ajouAnimer(this);
       }
    }
   
    
}
