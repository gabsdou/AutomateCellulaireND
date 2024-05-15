public class Si extends Operateur{
    public int calcule(int[] arg){
        return arg[1] != 0 ? arg[2] : arg[3];
    }
}