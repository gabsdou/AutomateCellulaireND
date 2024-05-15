import java.util.ArrayList;


public class Noeud{

    private Operateur operation;
    private ArrayList<Noeud> nodes;

    public Noeud(Operateur op){
        operation = op;
        nodes = new ArrayList<Noeud>();
    }

    public Noeud branch(i){
        return nodes.get(i);
    }

    public void addNoeud(Operateur o){
        nodes.add(new Noeud(o));
    }

    public void removeNoeud(Noeud n){
        nodes.remove(n);
    }

    public Operateur getOperation(){
        return operation;
    }

}