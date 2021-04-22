JAVAC=/usr/bin/javac
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES= BinaryTreeNode.class BTQueueNode.class BTQueue.class BinaryTree.class AVLTree.class ReadFile.class Student.class AccessAVLApp.class Experiment.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class
runavl: $(CLASS_FILES)
	java -cp bin AccessAVLApp
runexperiment:
	java -cp bin Experiment
doc:
	javadoc -sourcepath src -d doc src/*.java
cleanexperiment:
	rm data/experiment/*
