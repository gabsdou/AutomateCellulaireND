
public class Add extends Operateur{
    public Add(){
        super(true);
    }
    public int calcule(int[] arg){
        return arg[0] + arg[1];
    }
}
