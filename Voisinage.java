import java.util.*;

public class Voisinage implements Iterator{
    private int nbVoisin;
    private int dimension;
    private int[][] n_plet;
    private int iteration;
    private Dimension dim

    public Voisinage(int nbVoisin, int dimension, Dimension dim){
        this.nbVoisin = nbVoisin;
        this.dimension = dimension;
        iteration = 0;
        this.dim = dim;
        n_plet = new int[nbVoisin][dimension];

    }

    public Voisinage(int nbVoisin, int dimension){
        this(nbVoisin,dimension,NULL);
    }


    public boolean hasVoisin(){
        if(iteration < nbVoisin){
            return true;
        }
        return false;
    }

    public int[] nextVoisin(){
        if(!hasVoisin){
            return NULL;
        }
        iteration++;
        return n_plet[iteration - 1];
    }
    public void resetIterator(){
        iteration = 0;
    }
    public int nextValueFromVoisin(){
        if(!hasVoisin){
            return -1;
        }
        int[] nplet = nextVoisin();
        return dim.get(nplet);
    }
}
