public class Execution {
    Operateur op;
    Dimension dimension;

    public Execution(Operateur op, Dimension dimension) {
        this.op = op;
        this.dimension = dimension;
    }
    void run(int[] dimsize){
        int max[] = new int[dimsize.length];
        int[] coords = new int[dimsize.length];
        for(int i = 1; i < dimsize.length; i++){
            max[i] = 1;
            max[i] = max[i-1] * dimsize[i];
        }
        for(int i = 0; i < max; i++){
            for(int j = 0; j < dimsize.length; j++){
                coords[j] = (i / max[j]) % dimsize[j];
            }
            Voisinage.position = coords;
            dimension.set(coords,Operateur.evaluer(op));
        }
    }
}
