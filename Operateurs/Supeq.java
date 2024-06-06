

public class Supeq extends Operateur{
    public Supeq(){
        super(true);
    }
    public int calcule(int[] arg){
        return arg[1] >= arg[2] ? 1 : 0;
    }
}
