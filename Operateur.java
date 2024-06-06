public abstract class Operateur{
    private Operateur[] args;
    public const boolean isOperation;

    public Operateur(Operateur... args, boolean type){
        this.args = args;
        this.isOperation = type;

    }

    public Operateur(){
        this.args = new Operateur[0];
        this.isOperation = false;
    }

    public static int evaluer(Operateur op){

            if(op.isOperation) {
                return op.getValue();
            }
            else{
                return op.calcule(getValue());
            }
    }
//analyser caractère par caractère la phrase
//Si c'est un chiffre, c'est une constante numérique
//Si c'est des lettres, faut lire jusqu'a la parenthese ouvrante,
// En gros faut lire jusqu'a une parenthese ouvrante, fermante ou virgule
// Construire un arbre, avec des noeuds
    public abstract int calcule(int[]);



    public int getValue(){
        int[] values = new int[args.length];

        for(int i = 0; i < args.length; i++){
            values[i] = evaluer(args[i]);
        }
        return calcule(values);
    }
    public Operateur[] getArgs(){
        return this.args;
    }

    public void AddOperation(...Operateur op){
        this.args = op;
    }


    public static Operateur buildTree(String s, int index){
       int start = 0;
       int parenthese = 0;
       while(s[index] != '('){
           index++;
       }
       parenthese++;
       Operateur op = operratorFromString(s.substring(start,index));
       start = index;
       while(parathese != 0){
           index++;
           if(s[index] == '('){
               parenthese++;
           }
           if(s[index] == ')'){
               parenthese--;
           }
           if(s[index] == ','){
               op.AddOperation(buildTree(s.substring(start,index),0));
               start = index;
           }
       }
       return op;
    }

}
