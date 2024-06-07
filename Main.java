import javax.swing.JFrame;
import java.util.Random;
import java.util.*;




public class Main{
    public static void main(String[] args){
        XmlParser xml = new XmlParser(args[0]);
        int[] incr = xml.getCoupe();
        int x = -1;
        int y = -1;
        for(int i = 0; i < incr.length; i++){
            if(x < 0 && incr[i] < 0){
                x = i;
                incr[i] = ++incr[i];
                continue;
            }
            if(incr[i] < 0){
                y = i;
                break;
            }

        }
        Dimension d = xml.getDimension();
        Operateur op = xml.getOperateur();
        Execution e = new Execution(op,d);

        ArrayList<Integer> coord = new ArrayList<Integer>();
        Dimension.getDimSize(d, coord);
        int[] tab = coord.stream().mapToInt(i -> i).toArray();
        Interface inter = null;
        if(tab.length == 1){
            inter = new Interface(tab[x],tab[x],8);
        }
        else{
            inter = new Interface(tab[y],tab[x],8);
        }
        while(true){
            while(inter.getPlay() == false){
                continue;
            }
            if(tab.length == 1){
                for(int i=0;i< tab[0];i++){
                    try{
                        Thread.sleep(50);
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
            else{// +1 dimension

                for(int i=0;i< tab[0];i++){
                    for(int j = 0; j < tab[1]; j++){
                        inter.tuerCase(j,i);
                        incr[x] = i;
                        incr[y] = j;
                        if(d.get(incr) == 1){
                            inter.colorierCase(j,i);
                        }

                    }
                }
                try{
                    Thread.sleep(50);
                }catch(InterruptedException ex){
                    System.out.println("erreur");
                }
                e.run(tab);
                inter.repaint();
            }



        }



    }
}
