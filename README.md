COMMANDES:
	make -> compiler
	make run -> lancer l'application
	make test -> lancer les test
	make clean -> supprimer les binaires
	make doc -> generer la javadoc

GIT: https://dwarves.iut-fbleau.fr/git/bertholi/Kahmate

Trello: https://trello.com/b/7kkg7pD9/kahmate

# CONVENTIONS

Normes de programmation:
* Tout en anglais
* Faire la javadoc en même temps (javadoc en anglais)
* Commenter les sections peu compréhensibles
* Suivre les conventions du java:
    * Classe: Camel case (ex: BonjourJeSuisUneClasse)
    * Variables: Lower Camel case (ex: jeSuisUneVariable)
    * Méthodes: Lower Camel case (ex: jeSuisUneMéthode)
    * Constantes: Upper case avec underscore (ex: JE_SUIS_UNE_CONSTANTE)
    * Indentation avec 1 tab (régler son éditeur de texte)
    * Lisibilité des opérations: int a = b + c; (PAS CA SVP: int a=b+c;)
* Réutiliser un maximum les noms de variables pour respecter les conventions
* Classes Vue: MaVueView
* Classes Controleur: MonControleurController
* Classes Modèles: MonModèleModel
* Essayer de minimiser les imports. Si il y en a trop, les organiser

Trello:
* Trello va nous servir à gérer les tâches à réaliser
* Les tâches sont créées UNIQUEMENT par le product owner
* Chaque tâche possède un numéro (RQT-234 -> request)
* Il y a une catégorie pour chaque domaine de tâche
* Chaque tâche possède un status: TODO, IN PROGRESS, REVIEW, DONE
    * Etapes de réalisation d'une tâche:
        * Travailler sur une copie à jour du git (git pull)
        * Passer la tâche en IN PROGRESS
        * S'ajouter à la tâche (add member)
        * Une fois finie, ajouter une petite description, note pour savoir ce qui a été fait
        * Passer la tâche en REVIEW si nécessaire (une autre personne devra se charger de revoir le code)
        * Passer la tâche en DONE
        * Ajouter les changements à la branche (git push), gérer les merges si nécessaire

Structure du projet:

<pre>
Projet
    \_bin -> le projet est généré dans ce répertoire
    \_docs -> toutes les documentations (UML, diagrammes, Words...) sont ici
    \_rsc -> ressources de l'application, le chemin d'accès classique sera ../rsc/image.png dans le code
    \_src -> toutes les sources
        \_engine -> codes moteurs (business)
            \_interface -> interface à utiliser par les autres systèmes
            \_...
        \_ai -> codes intelligence artificielle
            \_interface -> interface à utiliser par les autres systèmes
            \_...
        \_gui -> partie graphique de l'application
            \_interface -> interface à utiliser par les autres systèmes
            \_...
        \_launcher -> lancement des parties supérieurs
    .gitignore
    Makefile
    README.md
</pre>