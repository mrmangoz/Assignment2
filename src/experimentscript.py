# Python script to automate testing varying dataset sizes

import os
import random as r

file = open("data/oklistraw.txt", "r") # opens original oklist file
lines = file.readlines() # list of all lines in original oklist file


def randomLines(n):
    # takes in dataset size and populates a list of 0 to dataset-1
    linesList = [i for i in range(n-1, -1, -1)]
    r.shuffle(linesList) # shuffles the list
    return linesList

'''def runAVLApp(datasetSize):
    with open("data/oklist.txt", "r") as dfile:
        for line in dfile:
            # loops through each line in new oklist
            studentID = str(line.strip().split(" ")[0])
            os.system("java -cp bin AccessAVLApp " + studentID) # runs java program in command-line
            with open("data/instrumentation/AccessAVLAppFindCount.txt", "r") as countfile:
                # retrieves the operation count outputted by java program
                count = countfile.read()
                with open("data/experiment/AccessAVLAppFind" + str(datasetSize) + ".txt", "a") as countstore:
                    countstore.write(studentID + " " + count + "\n") # writes student ID and operation count to new text file

    with open("data/instrumentation/AccessAVLAppInsertCount.txt", "r") as countfile:
        # retrieves the operation count outputted by java program
        count = countfile.read()
        with open("data/experiment/AccessAVLAppInsert" + str(datasetSize) + ".txt", "a") as countstore:
            countstore.write(count) # writes student ID and operation count to new text file'''
def runExperiment(datasetSize):
    os.system("java -cp bin Experiment " + datasetSize)

for n in range(1,11):
    # loops through datasetSize = 500,1000,1500....5000
    datasetSize = n*500
    subsetLinePos = randomLines(datasetSize) # generates the list of random positions
    varyingfile = open("data/oklist.txt", "w")
    for i in range(datasetSize):
        varyingfile.write(lines[subsetLinePos[i]]) # retrieves the line corresponding to the random position allocated
                                                   # from the original oklist
    varyingfile.close()

    runExperiment(str(datasetSize))
    
    #runAccessBSTApp(datasetSize)
