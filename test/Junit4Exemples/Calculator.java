
/**
   Calculator est une classe offrant une seule méthode qui évalue une somme, donnée sous la forme d'une chaîne de caractère listant des opérandes séparées par des +

*/

public class Calculator {

    /**
       somme les opérandes passées sous forme d'une chaîne de caractères et retourne le résultat sous forme d'entier.
       @param expression : chaîne de caractères ("nombres" séparés par des + sans espaces), par exemple "42+3" ou encore "-42+42" (le moins unaire est autorisé).
                           ici nombre est à comprendre au sens de  parseInt(java.lang.String)
       @throws NumberFormatException : si l'expression n'est pas dans ce format (par exemple "x+2" ou " 1 +2" -- il y a des espaces -- ou encore "9999999990").
     */    
    public int evaluate(String expression) {
        int sum = 0;
        for (String summand: expression.split("\\+"))
            sum += Integer.valueOf(summand);
        return sum;
    }

    /**
       Pour appeller cette super méthode depuis la ligne de commande (on ne regarde que le premier argument, les autres sont ignorés).
 
     */   
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.evaluate(args[0]));
    }
}
