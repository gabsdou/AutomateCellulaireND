import javax.swing.JFrame;
import java.util.Random;
import java.util.*;




public class Main{
    final static int H = 80;
    final static int W = 80;
    public static void main(String[] args){
        int posx, posy;
        System.out.println("debut du programme\n");
        Dimension d = new Dimension(2,0,new int[]{H,W});
        System.out.println("Dimension creer\n");

        Voisinage g8 = new Voisinage(8,2,new int[][]{{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{-1,1},{0,1},{1,1}});
        Voisinage g8e = new Voisinage(9,2,new int[][]{{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{-1,1},{0,1},{1,1},{0,0}});
        Voisinage g0 = new Voisinage(1,2,new int[][]{{0,0}});
        System.out.println("Voisinage creer\n");

        Dictionary<String,Voisinage> gk = new Hashtable<String,Voisinage>();
        gk.put("G8",g8);
        gk.put("G8E",g8e);
        gk.put("G0",g0);
        Operateur op = Operateur.buildTree("OU(SI(EQ(COMPTER(G8E),3),1,0),SI(EQ(COMPTER(G8),3),1,0))",0,gk);
        System.out.println("Arbre creer\n");

        Execution e = new Execution(op,d);
        Interface inter = new Interface(H,W,8);
        Random r = new Random();
        int i;
  //TODO: tg


        //adding glider
        d.set(1,10,10);
        d.set(1,11,11);
        d.set(1,11,12);
        d.set(1,10,12);
        d.set(1,9,12);




        for(i=0;i< H;i++){
            for(int j = 0; j < W; j++){
                if(d.get(i,j) == 1){
                    inter.colorierCase(i,j);
                }
            }
        }
        while(true){
            try{
                Thread.sleep(100);
            }catch(InterruptedException ex){
                System.out.println("erreur");
            }
            e.run(new int[]{H,W});
            for(i=0;i< H;i++){
                for(int j = 0; j < W; j++){
                    if(d.get(i,j) == 1){
                        inter.colorierCase(i,j);
                    }
                    else{inter.tuerCase(i,j);}
                }
            }
        }



    }
}
