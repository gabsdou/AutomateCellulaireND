



public class Sup extends Operateur{
    public Sup(){
        super(true);
    }
    public int calcule(int[] arg){
        return arg[0] > arg[1] ? 1 : 0;
    }
}
