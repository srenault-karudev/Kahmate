/**
   
   Example d'utilisation d'un runner spécial : Parameterized.
   
   Ce runner permet de facilement itérer des tests similaires sur plusieurs choix de valeurs. 
   

 */
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


// l'annotation @RunWith propre aux runners
@RunWith(Parameterized.class)
public class TestParam {

    // l'annotation @Parameters pour aller chercher les paramètres des tests (on itère sur des tuples d'objet)
    @Parameters(name = "{index}: {0} = {2}")
     public static Iterable<Object[]> data() {
         return Arrays.asList(new Object[][] { { "1+2+3", 6 }, { "40+2", 42 },
                 { "001+41", 42 }, { "0000", 0 }, { "999+-999", 0 } });
     }

    // les attributs qui correspondent aux éléments de nos tuples
     private String expression;
     private int res;

    // le constructeur
     public TestParam(String expression, int res) {
         this.expression = expression;
         this.res = res;
     }

    // le test qui va manger les paramètres qu'on vient de définir
     @Test
     public void evaluatesGoodExpressions() {
         Calculator calculator=new Calculator();    
         assertEquals(res, calculator.evaluate(expression));
     }
}
