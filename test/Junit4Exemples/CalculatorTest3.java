import static org.junit.Assert.assertEquals; 
import org.junit.Test;

/**
  CalculatorTest3 est un exemple de test pour la classe Calculator utilisant junit4 qui est volontairement non satisfait

 */

public class CalculatorTest3 {

    
    @Test
    public void evaluatesGoodExpression() throws Exception{
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2+3");
        assertEquals(42, sum);
    }


}
