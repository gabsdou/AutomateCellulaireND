

public class Dimension implements TabDyn{
    private int dimension;
    private TabDyn[] case;
    private int length;
    private int value;


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
    
    public Dimension(int dimension, int length){
        int[] dimsize = new int[dimension];
        for(int i : dimSize) 
            i = length;
        this(dimension,0,dimsize);
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
        d.setValue(val);
    }


    private int getValue(){
        if(dimension > 0){
            return -1
        }
        return this;
    }

    
}