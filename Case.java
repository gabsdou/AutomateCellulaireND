import java.util.*;

public class Case implements TabDyn{
    private int value;

    public Case(int value){
        this.value = value;
    }

    public Case(){
        this(0);
    }

    public TabDyn getDim(int i){
        System.out.println("not a dimenssion");
        return null;
    }

    public void setValue(int val){
        value = val;
    }


    public int getValue(){
        return value;
    }

}
