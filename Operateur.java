import java.util.*;

public abstract class Operateur{
    private ArrayList<Operateur> args;
    public boolean isOperation;



    public Operateur(boolean isOperation){
        this.args = new ArrayList<Operateur>();
        this.isOperation = isOperation;
    }

    public static int evaluer(Operateur op){

        return op.getValue();
    }
//analyser caractère par caractère la phrase
//Si c'est un chiffre, c'est une constante numérique
//Si c'est des lettres, faut lire jusqu'a la parenthese ouvrante,
// En gros faut lire jusqu'a une parenthese ouvrante, fermante ou virgule
// Construire un arbre, avec des noeuds
    public abstract int calcule(int[] c);



    public int getValue(){
        int[] values = new int[args.size()];

        for(int i = 0; i < args.size(); i++){
            values[i] = evaluer(args.get(i));
        }
        return calcule(values);
    }
    public ArrayList<Operateur> getArgs(){
        return this.args;
    }

    public void AddOperation(Operateur op){
        this.args.add(op);
    }


    public static Operateur buildTree(String s, int index, Dictionary<String,Voisinage> gk){
       int start = 0;
       int parenthese = 0;
       while(s.charAt(index) != '('){
           index++;
       }
       parenthese++;
       Operateur op = operratorFromString(s.substring(start,index));
       start = index;
       while(parenthese != 0){
           index++;
           if(s.charAt(index) == '('){
               parenthese++;
           }
           if(s.charAt(index) == ')'){
               parenthese--;
           }
           if(s.charAt(index) == ','){
               op.AddOperation(buildTree(s.substring(start,index),0,gk));
               start = index;
           }
       }
       if(op instanceof Compter){
           Compter c = (Compter)op;
           c.setVoisinage(gk.get(s.substring(start,index)));
       }
       else{
           op.AddOperation(buildTree(s.substring(start,index),0,gk));
       }
       return op;
    }

    public static Operateur operratorFromString(String s){
        switch(s){
            case "SI":
                return new Si();
            case "Add":
                return new Add();
            case "Sub":
                return new Sub();
            case "Mul":
                return new Mul();
            case "Non":
                return new Non();
            case "Ou":
                return new Ou();
            case "Sup":
                return new Sup();
            case "Supeq":
                return new Supeq();
            case "Compter":
                return new Compter();
        }
        return null;
    }
}
