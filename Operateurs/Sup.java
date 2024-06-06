

public class Sup extends Operateur{
    public Sup(){
        super(true);
    }
    public int calcule(int[] arg){
        return arg[1] > arg[2] ? 1 : 0;
    }
}
