# Ensimag 2A POO - TP 2015/16
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     Pour un package (ici gui.jar), il est aussi dans bin.
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: testGUI testBalls testBallsSimulator testConwaySimulator testImmigrationSimulator testSegregationSimulator testBoidsSimulator testPredateursSimulator testEnsembleDesFonctions testEventManager

testGUI:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestGUI.java

testBalls:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/simulateurBalles/TestBalls.java

testBallsSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/simulateurBalles/TestBallsSimulator.java

testConwaySimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/jeuDeLaVieConway/TestGameOfLifeSimulator.java

testImmigrationSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/jeuDeLImmigration/TestGameOfImmigrationSimulator.java

testSegregationSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/segregationSchelling/TestSegregationSimulator.java

testBoidsSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/boids/TestBoidsSimulator.java

testEventManager:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/event/TestEventManager.java

testPredateursSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/boids/TestPredateursSimulator.java

testEnsembleDesFonctions:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestEnsembleDesFonctions.java


# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin TestGUI
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeIHM
exeGUI:
	java -classpath bin:bin/gui.jar TestGUI

exeBalls:
	java -classpath bin:bin/gui.jar simulateurBalles/TestBalls

exeBallsSimulator:
	java -classpath bin:bin/gui.jar simulateurBalles/TestBallsSimulator

exeConwaySimulator:
	java -classpath bin:bin/gui.jar jeuDeLaVieConway/TestGameOfLifeSimulator

exeImmigrationSimulator:
	java -classpath bin:bin/gui.jar jeuDeLImmigration/TestGameOfImmigrationSimulator

exeSegregationSimulator:
	java -classpath bin:bin/gui.jar segregationSchelling/TestSegregationSimulator

exeBoidsSimulator:
	java -classpath bin:bin/gui.jar boids/TestBoidsSimulator

exePredateursSimulator:
	java -classpath bin:bin/gui.jar boids/TestPredateursSimulator

exeEventManager:
	java -classpath bin:bin/gui.jar event/TestEventManager

exeEnsembleSimulations:
	java -classpath bin:bin/gui.jar TestEnsembleDesFonctions.java


#Documentation
docs: docsSimulateurBalles docsConway docsImmigration docsSegregation  docsBoids docsEvent

docsSimulateurBalles:
	javadoc -d docs -classpath bin/gui.jar -sourcepath src src/simulateurBalles/*.java

docsConway:
	javadoc -d docs -classpath bin/gui.jar -sourcepath src src/jeuDeLaVieConway/*.java

docsImmigration:
	javadoc -d docs -classpath bin/gui.jar -sourcepath src src/jeuDeLImmigration/*.java

docsSegregation:
	javadoc -d docs -classpath bin/gui.jar -sourcepath src src/segregationSchelling/*.java

docsBoids:
	javadoc -d docs -classpath bin/gui.jar -sourcepath src src/boids/*.java

docsEvent:
	javadoc -d docs -classpath bin/gui.jar -sourcepath src src/event/*.java


clean:
	rm -rf bin/*.class bin/simulateurBalles/*.class bin/jeuDeLaVieConway/*.class bin/jeuDeLImmigration/*.class bin/segregationSchelling/*.class bin/boids/*.class bin/event/*.class docs
