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
        System.out.println(tab[0]);
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
            if(tab.length == 1){//TODO : REPARER
                for(int i=0;i< 100;i++){
                    try{
                        Thread.sleep(100);
                    }catch(InterruptedException ex){
                        System.out.println("erreur");
                    }
                    for(int j = 0; j < 100; j++){
                        inter.tuerCase(j,i);
                        incr[x] = j + inter.getViewX();
                        if(d.get(j) == 1){
                            inter.colorierCase(j,i);
                        }
                    }
                    e.run(tab);
                    inter.repaint();
                }
            }
            else{// +1 dimension
                try{
                    Thread.sleep(10);
                }catch(InterruptedException ex){
                    System.out.println("erreur");
                }
                for(int i=0;i< 100;i++){
                    for(int j = 0; j < 100; j++){
                        inter.tuerCase(j,i);
                        incr[y] = i + inter.getViewY();
                        incr[x] = j + inter.getViewX();
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
