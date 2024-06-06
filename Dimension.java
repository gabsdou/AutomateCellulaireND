

public class Dimension implements TabDyn{
    private int dimension;
    private TabDyn[] case;
    private int length;


    public Dimension(int dimension, int index, int[] dimSize){
        this.dimension = dimension;
        length = dimSize[index];
        if(dimension > 1){
            case = new TabDyn[length];
            for(int i = 0; i < length; i++){
                case[i] = new Dimension(dimension-1,index+1,dimSize);
            }
        }
        if(dimension == 1){
            case = new TabDyn[length];
            for(int i = 0; i < length; i++){
                case[i] = new Case();
            }
        }
    }


    private TabDyn getDim(int i){
        return case[i];
    }

    private void SetValue(int val){
        this.value = val;
    }

    public int get(int... coords){
        TabDyn  d = dimension;
        for(int i : coords){
            d = d.getDim(i);
        }
        return d.getValue();
    }

      public int set(int... coords,int val){
        TabDyn d = dimension;
        for(int i : coords){
            d = d.getDim(i);
        }
        if(d instanceof Case){
            d.setValue(val);
        }
        else{
            throw new Exception("not a case");
        }

    }

    public int getPronfondeur(){
        return dimension;
    }

    public int getLength(){
        return length;
    }

    private int getValue(){
        if(dimension > 0){
            return -1
        }
        return this;
    }
}
