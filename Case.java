public class Case implements TabDyn{
    private int value;

    public Case(int value){
        this.value = value;
    }

    public Case(){
        this(0);
    }

    public TabDyn getDim(int i)throws Exception{
        throw new Exception("not a dimenssion");
        return null;
    }

    public void setValue(int val)throws Exception{
        value = val;
    }


    public int getValue()throws Exception{
        return value;
    }

}
