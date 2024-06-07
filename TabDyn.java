import java.util.ArrayList;

public interface TabDyn{

    public void setValue(int val);

    public int getValue();

    public TabDyn getDim(int i);

    public void addDimSize(ArrayList<Integer> dimSize);

}
