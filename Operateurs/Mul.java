

public class Mul extends Operateur{
    public Mul(){
        super(true);
    }
    public int calcule(int[] arg){
        return arg[1] * arg[2];
    }
}
