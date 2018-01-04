import java.util.ArrayList;

public abstract class Stockage<T> extends ArrayList<T> {

    public void ajouter(T o){
        add(o);
    }

    public boolean supprimer(T o){
        return remove(o);
    }

    public boolean existe(T o){
        return contains(o);
    }

    public int getTotal(){
        return this.size();
    }

    public abstract void afficher();
}
