public class Execution {
    private Operateur op;
    private static Dimension dimension;

    public Execution(Operateur op, Dimension dimension) {
        this.op = op;
        this.dimension = dimension;
    }
    void run(int[] dimsize){
        int max;
        int domain[] = new int[dimsize.length];
        int[] coords = new int[dimsize.length];
        domain[0] = 1;
        max = dimsize[0];
        for(int i = 1; i < dimsize.length; i++){
            domain[i] = 1;
            domain[i] = domain[i-1] * dimsize[i];
            max *= dimsize[i];
        }
        for(int i = 0; i < max; i++){
            for(int j = 0; j < dimsize.length; j++){
                coords[j] = (i / domain[j]) % dimsize[j];
            }
            Voisinage.position = coords;
            dimension.set(Operateur.evaluer(op),coords);
        }
    }

    public static Dimension getDimension(){
        return dimension;
    }
}
