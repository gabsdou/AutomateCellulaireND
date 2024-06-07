

public class Ou extends Operateur{
    public Ou(){
        super(true);
    }
    public int calcule(int[] arg){
        return arg[0] | arg[1];
    }
}
