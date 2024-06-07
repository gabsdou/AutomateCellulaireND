

public class Ou extends Operateur{
    public Ou(){
        super(true);
    }
    public int calcule(int[] arg){
        for(int i = 0; i < arg.length; i++){
            if(arg[i] == 1){
                return 1;
            }
        }
        return 0;
    }
}
