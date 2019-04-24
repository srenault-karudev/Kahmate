/**
   
   Alternative à la ligne de commande, on peut appeller le runner depuis java avec org.junit.runner.JUnitCore.runClasses
qui retourne un objet de type Result qui modélise les résultats des tests.

En particulier, on peut accéder à la liste des échecs -- un échec eest un objet Failure -- avec getFailures

 */


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunForestRun {

    public static void main(String[] args) {
        final Result result = org.junit.runner.JUnitCore.runClasses(CalculatorTest0.class,CalculatorTest1.class,CalculatorTest3.class);
        for (final Failure failure : result.getFailures()) {
            System.out.println(failure.toString()); // affiche détail sur chaque échec
        }
        System.out.println(result.wasSuccessful()); // affiche true ssi aucune erreurs
    }
}
