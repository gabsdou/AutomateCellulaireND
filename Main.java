import javax.swing.JFrame;
import java.util.Random;

public class Main{
    public static void main(String[] args){
        int posx, posy;
        Interface inter = new Interface(80,80,8);
        Random r = new Random();
        int i;
        for(i=0;i<30;i++){
            posx = r.nextInt(inter.getLargeur());
            posy = r.nextInt(inter.getHauteur());
            inter.colorierCase(posx,posy);
        }



    }
}
