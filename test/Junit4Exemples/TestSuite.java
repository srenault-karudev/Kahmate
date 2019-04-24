/**
  Un runner spécial qui sert à créer une collection de tests.
  Typiquement, on peut avoir une classe de tests unitaires pour chaque classe métier et une "suite de tests" qui va appeller tous les tests de classes ayant un lien (par exemple dans le même paquet).  

  Ceci est de nouveau un test, qu'on peut exécuter en faisant par exemple dans une console
  java org.junit.runner.JUnitCore TestSuite

 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// @RunWith permet d'indiquer le runner
@RunWith(Suite.class)

//les tests à faire et dans quel ordre.
@Suite.SuiteClasses({
  CalculatorTest0.class,
  CalculatorTest1.class,
  CalculatorTest3.class
})

public class TestSuite {
  // La classe est vide en fait
  // C'est juste un conteneur pour les annotations du dessus, utilisés par Junit4
}
