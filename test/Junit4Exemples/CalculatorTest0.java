import static org.junit.Assert.assertEquals; // import static : une facilité offerte par java5
import org.junit.Test;

/**
  CalculatorTest0 est un premier exemple de test pour la classe Calculator utilisant junit4
  Assert, ou comment vérifier qu'une méthode donne un résultat correct?

  Remarque en passant, pour tester en ligne de commande (une fois les classes compilées), il faut faire
  $java org.junit.runner.JUnitCore CalculatorTest0

  Remarque, comme expliqué dans la doc de org.junit.runner.JUnitCore
JUnitCore is a *facade* for running tests. It supports running JUnit 4 tests, JUnit 3.8.x tests, and mixtures. To run tests from the command line, run java org.junit.runner.JUnitCore TestClass1 TestClass2

  Oh le joli design pattern. C'est cadeau.
 */

public class CalculatorTest0 {

    
    // un test pour Junit4 c'est une méthode avec l'annotation suivante devant la méthode.
    @Test
    public void evaluatesGoodExpression() {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2+3");
        // on peut stipuler que des choses sont normalement égales (il faut charger de manière statique les Assert si on veut éviter d'avoir à écrire de quelle classe on parle)
        assertEquals(6, sum);
    }


}
