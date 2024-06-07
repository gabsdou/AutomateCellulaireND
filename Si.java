




public class Si extends Operateur{
    public Si(){
        super(true);
    }

    public int calcule(int[] arg){
        return arg[0] != 0 ? arg[1] : arg[2];
    }
}
