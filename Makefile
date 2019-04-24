SRC_DIR := src/
BIN_DIR := bin/
DOC_DIR := docs/
FLAGS := -d $(BIN_DIR) -sourcepath $(SRC_DIR) -classpath $(BIN_DIR)
JC := javac
JEXE := java -classpath "$(BIN_DIR)" Launcher
JDOC := javadoc -d $(DOC_DIR)
RM := rm -fr
MKDIR := mkdir
CPY := cp

all: $(BIN_DIR) $(DOC_DIR) $(BIN_DIR)Launcher.class

$(BIN_DIR):
	$(MKDIR) $(BIN_DIR)

$(BIN_DIR)Launcher.class: $(SRC_DIR)Launcher.java $(BIN_DIR)GUI.class
	$(JC) $(FLAGS) $(SRC_DIR)Launcher.java
$(BIN_DIR)GUI.class: $(SRC_DIR)gui/GUI.java $(BIN_DIR)TerrainLayer.class $(BIN_DIR)PlayersLayer.class $(BIN_DIR)AreaLayer.class $(BIN_DIR)MouseController.class
	$(JC) $(FLAGS) $(SRC_DIR)gui/GUI.java
$(BIN_DIR)TerrainLayer.class: $(SRC_DIR)gui/layers/TerrainLayer.java
	$(JC) $(FLAGS) $(SRC_DIR)gui/layers/TerrainLayer.java
$(BIN_DIR)PlayersLayer.class: $(SRC_DIR)gui/layers/PlayersLayer.java $(BIN_DIR)VisualPlayer.class
	$(JC) $(FLAGS) $(SRC_DIR)gui/layers/PlayersLayer.java
$(BIN_DIR)VisualPlayer.class: $(SRC_DIR)gui/models/VisualPlayer.java
	$(JC) $(FLAGS) $(SRC_DIR)gui/models/VisualPlayer.java
$(BIN_DIR)AreaLayer.class: $(SRC_DIR)gui/layers/AreaLayer.java
	$(JC) $(FLAGS) $(SRC_DIR)gui/layers/AreaLayer.java
$(BIN_DIR)MouseController.class: $(SRC_DIR)gui/controllers/MouseController.java $(BIN_DIR)AnimationRunnable.class
	$(JC) $(FLAGS) $(SRC_DIR)gui/controllers/MouseController.java
$(BIN_DIR)AnimationRunnable.class: $(SRC_DIR)gui/controllers/AnimationRunnable.java
	$(JC) $(FLAGS) $(SRC_DIR)gui/controllers/AnimationRunnable.java

#Launch program 
run:
	$(JEXE) Launcher

#Remove all compiled file from bin folder
clean:
	$(RM) $(BIN_DIR)

#Generate javadoc
doc:
	$(JDOC) -classpath src/ $(SRC_DIR)*.java

#Remove javadoc
docclean:
	$(RM) $(DOC_DIR)*.html
	$(RM) $(DOC_DIR)*.js
	$(RM) $(DOC_DIR)*.css
	$(RM) $(DOC_DIR)package-list

#Dev utility
dev: clean $(DOC_DIR) $(BIN_DIR)Launcher.class

test:
	java -classpath test/hamcrest-2.1.jar:test/hamcrest-core-2.1.jar:test/junit-4.13-beta-1.jar:.:src/ org.junit.runner.JUnitCore 

testcompile:
	javac -classpath test/hamcrest-2.1.jar:test/hamcrest-core-2.1.jar:test/junit-4.13-beta-1.jar:.:src GameStateTest.java

.PHONY: clean test doc testcompile