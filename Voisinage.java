import java.util.*;

public class Voisinage implements Iterator{
    private int nbVoisin;
    private int dimension;
    private int[][] n_plet;
    private int iteration;
    public static int[] position;

    public Voisinage(int nbVoisin, int dimension,int[][] n_plet){
        this.nbVoisin = nbVoisin;
        iteration = 0;
        this.n_plet = n_plet;

    }


    public boolean hasNext(){
        if(iteration < nbVoisin){
            return true;
        }
        return false;
    }

    public int[] next(){
        if(!hasNext()){
            return NULL;
        }
        iteration++;
        return n_plet[iteration - 1];
    }
    public void resetIterator(){
        iteration = 0;
    }
}
