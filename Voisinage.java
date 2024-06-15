public class Voisinage{
    private int nbVoisin;
    private int[][] n_plet;
    public static int[] position;

    public Voisinage(int nbVoisin, int dimension,int[][] n_plet){
        this.nbVoisin = nbVoisin;
        this.n_plet = n_plet;
    }
    
    public int[][] getVoisinage(){
        return n_plet;
    }
    public int getTaille(){
        return nbVoisin;
    }
}
