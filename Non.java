

public class Non extends Operateur{
    public Non(){
        super(true);
    }
    public int calcule(int[] arg){
        return 1 - arg[0];
    }
}
