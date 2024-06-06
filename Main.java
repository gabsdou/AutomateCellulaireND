import javax.swing.JFrame;
import java.util.Random;
import java.util.*;


public class Main{
    public static void main(String[] args){
        int posx, posy;
        Dimension d = new Dimension(2,0,new int[]{80,80});
        Voisinage g0 = new Voisinage(8,2,new int[][]{{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{-1,1},{0,1},{1,1}});
        Dictionary<String,Voisinage> gk = new Hashtable<String,Voisinage>();
        gk.put("g0",g0);
        Operateur op = Operateur.buildTree("ecrire ici",0,gk);
        Execution e = new Execution(op,d);
        Interface inter = new Interface(80,80,8);
        Random r = new Random();
        int i;
  //TODO: tg
        while(true){
            e.run(new int[]{80,80});
            for(i=0;i< 80;i++){
                for(int j = 0; j < 80; j++){
                    if(d.get(i,j) == 1){
                        inter.colorierCase(i,j);
                    }
                }
            }
        }



    }
}
