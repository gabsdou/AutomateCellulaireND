


public class Eq extends Operateur{
    public Eq(){
        super(true);
    }
    public int calcule(int[] arg){
        return arg[0] == arg[1] ? 1 : 0;
    }
}
