Les fichiers et ce qu'ils illustrent

Calculator.java         le fichier contenant la classe qu'on va prétendre couloir tester.

CalculatorTest0.java    mon premier test avec Junit4 et assert          
CalculatorTest1.java    mon second test avec Junit4 pour des exceptions
CalculatorTest2.java    les deux précédents
CalculatorTest3.java    un test volontairement non satisfait

Jusqu'ici pour exécuter un test, on compile tous les fichiers (une fois le classpath correct) puis on fait :

$java org.junit.runner.JUnitCore CalculatorTest0

voir plusieurs classes de tests suite à suite, en faisant :

$java org.junit.runner.JUnitCore CalculatorTest0 CalculatorTest1

Des choses un peu plus avancées

RunForestRun.java       un exemple de runner (alternative à la ligne de commande qui fait la même chose en java).
TestParam.java          mon premier test avancé permettant d'exécuter un test simple sur une liste de paramètres.
TestSuite.java          comment combiner plusieurs tests (par exemple si on veut tester plusieurs classes en même temps).
AssertTests.java        Squelette de toutes les variantes d'assertion proposées par Junit4

===

Pour pouvoir utiliser ces tests à bon escients, il faut :
_ avoir installé Junit4 (c'est un jar)
_ faire ce qu'il faut au CLASSPATH pour que Junit4 soit dedans.

Par exemple sur ma machine, j'ai plusieurs versions de junit:

$ ls -l /usr/share/java/junit*
-rw-r--r-- 1 root root 108762 mai   18  2012 /usr/share/java/junit-3.8.2.jar
-rw-r--r-- 1 root root 313072 mars   8  2016 /usr/share/java/junit4-4.12.jar
lrwxrwxrwx 1 root root     15 mars   8  2016 /usr/share/java/junit4.jar -> junit4-4.12.jar
lrwxrwxrwx 1 root root     15 mai   18  2012 /usr/share/java/junit.jar -> junit-3.8.2.jar

Du coup, j'ai fait en sorte que mon CLASSPATH contienne /usr/share/java/junit4.jar

$ echo $CLASSPATH 
.:/usr/lib/jvm/java-8-openjdk-amd64/lib:/usr/share/java/junit4.jar

