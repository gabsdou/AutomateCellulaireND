

public class Compter extends Operateur{
    private Voisinage voisinage;
    public Compter(){
        super(true);
    }
    public int calcule(int[] arg){
        int res = 0;
        int[] coords = new int[voisinage.getTaille()];
        for(int[] i : voisinage.getVoisinage()){
            for(int j = 0; j < i.length; j++){
                coords[j] = i[j] + arg[j];
            }
            res += Execution.getDimension().get(coords);
        }
        return res;
    }

    public void setVoisinage(Voisinage v){
        voisinage = v;
    }
}
