import java.util.*;


public class Dimension implements TabDyn {
    private int dimension;
    private TabDyn[] cellule;
    private int length;


    public Dimension(int dimension, int index, int[] dimSize){
        this.dimension = dimension;
        length = dimSize[index];
        if(dimension > 1){
            cellule = new TabDyn[length];
            for(int i = 0; i < length; i++){
                cellule[i] = new Dimension(dimension-1,index+1,dimSize);
            }
        }
        if(dimension == 1){
            cellule = new TabDyn[length];
            for(int i = 0; i < length; i++){
                cellule[i] = new Case();
            }
        }
    }


    public TabDyn getDim(int i){
        if(i >= cellule.length){
            return null;
        }
        if(i < 0){
            return null;
        }
        return cellule[i];
    }

    public void setValue(int val){
        System.out.println("not a case");
    }

    public int get(int... coords){
        TabDyn  d = this.getDim(coords[0]);
        if(d == null){
            return 0;
        }
        for(int i = 1; i < coords.length; i++){
            if(d == null){
                return 0;
            }
            d = d.getDim(coords[i]);
        }
        if(d == null){
                return 0;
            }
        return d.getValue();
    }

      public void set(int val,int... coords){
        TabDyn d = cellule[coords[0]];
        for(int i = 1; i < coords.length; i++){
            d = d.getDim(coords[i]);
        }

        d.setValue(val);



    }

    public int getPronfondeur(){
        return dimension;
    }

    public int getLength(){
        return length;
    }

    public int getValue(){
        System.out.println("not a case");
        return 0;
    }
}
