



public class Supeq extends Operateur{
    public Supeq(){
        super(true);
    }
    public int calcule(int[] arg){
        return arg[0] >= arg[1] ? 1 : 0;
    }
}
