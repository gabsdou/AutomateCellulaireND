


public class Valeur extends Operateur{
    int valeur;
    public Valeur(int v){
        super(false);
        valeur = v;
    }
    public int calcule(int[] arg){
        return valeur;
    }
}
