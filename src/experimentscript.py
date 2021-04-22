# Python script to automate testing varying dataset sizes

import os
import random as r
import shutil as s

file = open("data/oklistraw.txt", "r") # opens original oklist file
lines = file.readlines() # list of all lines in original oklist file

def runExperiment(datasetSize):
    os.system("java -cp bin Experiment " + datasetSize)

def createCriticalFiles(path, list):
    size = 0
    for sublist in list:
        minC, averageC, maxC = 0,0,0
        size += 500
        minC = min(sublist)
        averageC = sum(sublist)/len(sublist)
        maxC = max(sublist)
        criticalFile = open(path + str(size) + ".txt", "w")
        criticalFile.close()
        with open(path + str(size) + ".txt", "a") as criticalFile:
            criticalFile.write(str(minC) + "\n")
            criticalFile.write(str(averageC) + "\n")
            criticalFile.write(str(maxC) + "\n")

def criticalValues():
    criticalListFind = []
    criticalListInsert = []
    for n in range(500,5500, 500):
        tempFind = []
        tempInsert = []
        with open("data/experiment/AccessAVLAppFindCount" + str(n) + ".txt", "r") as findFile:
            for line in findFile:
                find = line.split()[1]
                tempFind.append(int(find))
        with open("data/experiment/AccessAVLAppInsertCount" + str(n) + ".txt", "r") as findFile:
            for line in findFile:
                insert = line.split()[1]
                tempInsert.append(int(insert))
        criticalListFind.append(tempFind)
        criticalListInsert.append(tempInsert)
    createCriticalFiles("data/experiment/findCritical", criticalListFind)
    createCriticalFiles("data/experiment/insertCritical", criticalListInsert)


for n in range(1,11):
    break
    datasetSize = n*500
    subsetLinePos = (lambda l: r.sample(l, datasetSize))([i for i in range(datasetSize-1, -1, -1)])
    varyingfile = open("data/oklist.txt", "w")
    for i in range(datasetSize):
        varyingfile.write(lines[subsetLinePos[i]]) # retrieves the line corresponding to the random position allocated
                                                   # from the original oklist
    varyingfile.close()
    runExperiment(str(datasetSize))
    s.move("data/instrumentation/AccessAVLAppInsertCount.txt", "data/experiment/AccessAVLAppInsertCount" + str(datasetSize) + ".txt")
    criticalValues()
