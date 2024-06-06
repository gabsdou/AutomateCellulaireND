
public class Sub extends Operateur{
    public Sub(){
        super(true);
    }
    public int calcule(int[] arg){
        return arg[1] - arg[2];
    }
}
