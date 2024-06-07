

public class Non extends Operateur{
    public Non(){
        super(true);
    }
    public int calcule(int[] arg){
        return ~arg[0];
    }
}
