public abstract class Operateur{
    private Operateur[] args;
    public const boolean isOperation;

    public Operateur(Operateur... args, boolean type){
        this.args = args;
        this.isOperation = type;

    }

    public int evaluer(Operateur op){
        
            if(op.isOperation) {
                return op.getValue();
            }
            else{
                return this.calcule(NULL);
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

}