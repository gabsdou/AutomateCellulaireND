public class Parser{
    private const String data;
    private int cursor;

    public Parser(String s){
        data = s;
        cursor = 0;
    }

    public Noeud parseToTree(){
        
    }

    public Noeud creerNoeudFromStr(String Name){
        Operateur op;
        switch(Name){
            case ""
        }
    }

    public String nextGetToken(char c){
        String tokens = new String();
        for(; cursor < data.length; cursor++ ){
            if(data[cursor] == c) return tokens;
            tokens.concat(s);
        }
        return tokens;

    }
}