public class Case implements TabDyn{
    private int value;

    public Case(int value){
        this.value = value; 
    }

    public Case(){
        this(0);
    }

    private TabDyn getDim(int i){
        throw new Exception("not a dimenssion");
        return NULL;
    }

    private void SetValue(int val){
        value = val;
    }


    private int getValue(){
        return value;
    }

    private TabDyn getDim(int i){
        return this;
    }

}