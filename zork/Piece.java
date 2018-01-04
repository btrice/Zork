   
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class Piece extends Stockage<Data>{
    private String description;
    private Map sorties;

    public Piece(String description) {
        this.description = description;
        sorties = new HashMap();
    }

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

    public String descriptionCourte() {
        return description;
    }

    public String descriptionLongue() {
        return "Vous etes dans " + description + ".\n" + descriptionSorties();
    }

    public String descriptionSorties() {
        String resulString = "Sorties:";
        Set keys = sorties.keySet();
        for (Iterator iter = keys.iterator(); iter.hasNext(); ) {
            resulString += " " + iter.next();
        }
        return resulString;
    }

    public Piece pieceSuivante(String direction) {
        return (Piece) sorties.get(direction);
    }

    public void afficher() {
        System.out.println("******************************************************************************");
        System.out.println("  Il y'a "+ this.getTotal() +" objets dans la pièces "+ descriptionCourte()+" :");
        System.out.println("*******************************************************************************");
        if (!this.isEmpty()) {
            Iterator it = this.iterator();
            while (it.hasNext()) {
                Data o = (Data) it.next();
                System.out.println("Nom de l'objet: " + o.getNom() + "       poids: " + o.getPoids() + " KGS");
                System.out.println("");
            }
        }
        else {
            System.out.println("IL N'Y A PAS D'OBJET DANS CETTE PIECE");
        }
    }

    public MonObjet rechercher(String nom) {
        if (!this.isEmpty()) {
            Iterator it = this.iterator();
            while (it.hasNext()) {
                Object o = it.next();
                if (o instanceof MonObjet) {
                    MonObjet ob = (MonObjet) o;
                    if( ob.getNom().equalsIgnoreCase(nom)) {
                        return ob;
                    }
                }
            }
        }
        return null;

    }

    public Animer getAnimer(){
        if (!this.isEmpty()) {
            Iterator it = this.iterator();
            while (it.hasNext()) {
                Object o = it.next();
                if (o instanceof Animer) {
                    return (Animer) o;
                }
            }
        }
        return null;
    }
}
