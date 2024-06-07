import javax.swing.JFrame;
import java.util.Random;
import java.util.*;




public class Main{
    final static int H = 100;
    final static int W = 100;
    public static void main(String[] args){
        XmlParser xml = new XmlParser(args[0]);
        Dimension d = xml.getDimension();
        Operateur op = xml.getOperateur();
        Execution e = new Execution(op,d);
        ArrayList<Integer> coord = new ArrayList<Integer>();
        Dimension.getDimSize(d, coord);
        int[] tab = coord.stream().mapToInt(i -> i).toArray();
        Interface inter = new Interface(tab[0],tab[0],8);
        while(true){
            if(tab.length == 1){
                for(int i=0;i< tab[0];i++){
                    try{
                        Thread.sleep(100);
                    }catch(InterruptedException ex){
                        System.out.println("erreur");
                    }
                    for(int j = 0; j < tab[0]; j++){
                        inter.tuerCase(j,i);
                        if(d.get(j) == 1){
                            inter.colorierCase(j,i);
                        }
                        
                    }
                    e.run(tab);
                    inter.repaint();
                }
            }
            else{
                
                for(int i=0;i< tab[0];i++){
                    for(int j = 0; j < tab[1]; j++){
                        inter.tuerCase(j,i);
                        if(d.get(i,j) == 1){
                            inter.colorierCase(j,i);
                        }
                        
                    }
                }
                try{
                    Thread.sleep(100);
                }catch(InterruptedException ex){
                    System.out.println("erreur");
                }
                e.run(tab);
                inter.repaint();
            }

           
           
        }



    }
}
