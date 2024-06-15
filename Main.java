import java.util.*;

public class Main{
    public static void main(String[] args){
        int size = 8;
        try {
            size = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Valeur par default 8");
        }
        Interface inter = new Interface(200,200,size);
        while(inter.getPath() == null){
            System.out.println("Waiting for a file to be selected");
            continue;
        }
        System.out.println(inter.getPath() + " selected");
        XmlParser xml = new XmlParser(inter.getPath());
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
        while(true){
            if(tab.length == 1){
                for(int i=0;i< Interface.VIEW_SIZE;i++){
                    try{
                        Thread.sleep(100);
                    }catch(InterruptedException ex){
                        System.out.println("erreur");
                    }
                    for(int j = 0; j < Interface.VIEW_SIZE; j++){
                        inter.tuerCase(j,i);
                        incr[x] = j + inter.getViewX();
                        if(d.get(incr) == 1){
                            inter.colorierCase(j,i);
                        }
                    }
                    while(!inter.getPlay()){System.out.println("waiting for play");}
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
                for(int i=0;i< Interface.VIEW_SIZE;i++){
                    for(int j = 0; j < Interface.VIEW_SIZE; j++){
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
                if(inter.getPlay())
                    e.run(tab);
                inter.repaint();
            }
        }
    }
}
