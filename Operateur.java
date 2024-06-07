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
        if(s.charAt(0) == ','){
            s = s.substring(1,s.length());
        }
        try{
            int i = Integer.parseInt(s);
            return new Valeur(i);
        }catch(NumberFormatException e){}
        System.out.println(s);
        int start = 0;
        int parenthese = 0;
        while(s.charAt(index) != '('){
            index++;
        }
        parenthese++;
        System.out.println(s.substring(start,index));
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
            if(s.charAt(index) == ',' && parenthese == 1){
                op.AddOperation(buildTree(s.substring(start+1,index),0,gk));
                start = index;
            }
        }
        if(op instanceof Compter){
            Compter c = (Compter)op;
            System.out.println(s.substring(start+1,index));
            c.setVoisinage(gk.get(s.substring(start+1,index)));
        }
        else{
            op.AddOperation(buildTree(s.substring(start,index),0,gk));
        }
        return op;
    }

    public static Operateur operratorFromString(String s){
        if(s.charAt(0) == ','){
            s = s.substring(1,s.length());
        }
        switch(s){
            case "SI":
                return new Si();
            case "ADD":
                return new Add();
            case "SUB":
                return new Sub();
            case "MUL":
                return new Mul();
            case "NON":
                return new Non();
            case "OU":
                return new Ou();
            case "SUP":
                return new Sup();
            case "EQ":
                return new Eq();
            case "SUPEQ":
                return new Supeq();
            case "COMPTER":
                return new Compter();
            case "ET":
                return new Et();
            default:
                return null;
        }
    }
}
