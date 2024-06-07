

public class Et extends Operateur{
    public Et(){
        super(true);
    }
    public int calcule(int[] arg){
        if(arg[0] == 1 && arg[1] == 1){
            return 1;
        }
        return 0;
    }
}