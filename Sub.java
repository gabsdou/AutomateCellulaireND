

public class Sub extends Operateur{
    public Sub(){
        super(true);
    }
    public int calcule(int[] arg){
        return arg[0] - arg[1];
    }
}
