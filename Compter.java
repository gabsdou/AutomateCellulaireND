

public class Compter extends Operateur{
    private Voisinage voisinage;
    public Compter(){
        super(true);
    }
    public int calcule(int[] arg){
        int res = 0;
        int[] coords = new int[voisinage.position.length];
        for(int[] i : voisinage.getVoisinage()){
            for(int j = 0; j < i.length; j++){
                coords[j] = i[j] + Voisinage.position[j];

            }
            Dimension d = Execution.getDimension();
            if(d == null){
                System.out.println("d est null");
            }

            res += d.get(coords);
        }
        return res;
    }

    public void setVoisinage(Voisinage v){
        voisinage = v;
    }
}
