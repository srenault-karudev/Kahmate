import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
  CalculatorTest1 est un exemple de test pour la classe Calculator utilisant junit4.
  Comment vérifier qu'on lance bien une exception?
 */

public class CalculatorTest1 {

    
    // un test pour Junit4 qui cherche à vérifier qu'il y a bien une exception
    @Test(expected = NumberFormatException.class) 
    public void doesNotEvaluateBadExpression() {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1 +2+3");//notez l'espace qui va génèrez une exception
    }
}
