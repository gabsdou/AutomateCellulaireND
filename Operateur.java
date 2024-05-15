public abstract class Operateur{
    public int evaluer(int operande1){
        if operande1.getClass() == Operateur.class {
            return this.evaluer(operande1.evaluer());
        }
        else if(operande1.getClass()==int){
            return operande1;
        }
    }

//analyser caractère par caractère la phrase  
//Si c'est un chiffre, c'est une constante numérique
//Si c'est des lettres, faut lire jusqu'a la parenthese ouvrante,
// En gros faut lire jusqu'a une parenthese ouvrante, fermante ou virgule
// Construire un arbre, avec des noeuds
    
    static public int and(int a, int b){
        return a & b;
    }
    static public int or(int a, int b){
        return a | b;
    }
    static public int not(int a){
        return ~a;
    }
    static public int sup(int a, int b){
        return a > b ? 1 : 0;
    }
    static public int supeq(int a, int b){
        return a >= b ? 1 : 0;
    }
    static public int eq(int a, int b){
        return a == b ? 1 : 0;
    }
    static public int count(Voisinage v){
        int cpt=0;
        for(int i=0; i<v.nbVoisin; i++){
        }
    }
    static public int add(int a, int b){
        return a+b;
    }
    static public int sub(int a, int b){
        return a-b;
    }
    static public int mul(int a, int b){
        return a*b;
    }
    static public int si(int a, int b, int c){
        return a != 0 ? b : c;
    }

}
