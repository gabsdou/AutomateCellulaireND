


public class Execution {
    private Operateur op;
    public static Dimension dimension;

    public Execution(Operateur op, Dimension dimension) {
        this.op = op;
        this.dimension = dimension;
    }
    public void run(int[] dimsize){
        int max;
        int domain[] = new int[dimsize.length];
        int[] coords = new int[dimsize.length];
        Dimension dimTmp = new Dimension(dimsize.length,0,dimsize);
        domain[0] = 1;
        max = dimsize[0];
        for(int i = 1; i < dimsize.length; i++){
            domain[i] = 1;
            domain[i] = domain[i-1] * dimsize[i - 1];
            max *= dimsize[i];
        }
         for(int i = 0; i < max; i++){
            for(int j = 0; j < dimsize.length; j++){
                coords[j] = (i / domain[j]) % dimsize[j];
                //System.out.print(coords[j] + " ");
            }
        }
        for(int i = 0; i < max; i++){
            for(int j = 0; j < dimsize.length; j++){
                coords[j] = (i / domain[j]) % dimsize[j];
                //System.out.print(coords[j] + " ");
            }

            Voisinage.position = coords;
            dimTmp.set(Operateur.evaluer(op),coords);

        }
        for(int i = 0; i < max; i++){
            for(int j = 0; j < dimsize.length; j++){
                coords[j] = (i / domain[j]) % dimsize[j];
                //System.out.print(coords[j] + " ");
            }
            dimension.set(dimTmp.get(coords),coords);
        }
    }

    public static Dimension getDimension(){
        return dimension;
    }
}
