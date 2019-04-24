import static org.junit.Assert.assertEquals;
import org.junit.Test;

import org.junit.BeforeClass; // ne pas oublie de charger le fait qu'on veut l'annotation @BeforeClass
import org.junit.AfterClass; 

/**
  
   CalculatorTest2 est un exemple de test pour la classe Calculator utilisant junit4
   Il réunit en fait les deux tests des 2 classes précédentes dans une seule classe.

   Typiquement on a en effet tous les tests simples portant sur une classe "métier" regroupée dans une classe de test correspondante.
   Avec les annotations, on peut factoriser des choses concernant tous ces tests. 

 */

public class CalculatorTest2 {
    static Calculator calculator;

    // On peut si on le souhaite faire un traitement avant tous les tests (typiquement on fait quelque chose de cher comme se connecter à une base de données, ici j'économise une instance de Calculator (on s'en moque un peu pour être honnête). 
    @BeforeClass
    public static void setUp() {
        System.out.println("Avant tous les tests");
        calculator = new Calculator();
    }
    
    @Test
    public void evaluatesGoodExpression() {
        System.out.println("Test evaluation bonne expression");
        int sum = calculator.evaluate("1+2+3");
        assertEquals(6, sum);
    }


    @Test(expected = NumberFormatException.class) 
    public void doesNotEvaluateBadExpression() {
        System.out.println("Test evaluation mauvaise expression");
        int sum = calculator.evaluate("1 +2+3");
    }
    
    // On peut si on le souhaite faire un traitement après tous les tests (typiquement on fait quelque chose de cher comme se connecter à une base de données, ici j'économise une instance de Calculator (on s'en moque un peu pour être honnête). 
    @AfterClass
    public static void tearDown() {
        System.out.println("Après tous les Test");
        calculator = null;
    }

}
