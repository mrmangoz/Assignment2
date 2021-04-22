import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

/**
  * Main java method for executing experiment. Implements methods from AccessAVLApp.
  * Stores all find operation counters in a String variable in-memory, to optimise speed, then writes to a text file.
  * After AVL Tree has been built, writes the insert operation
  */
public class Experiment {
  public static void main (String args[]) throws FileNotFoundException {
    try {
      String findCountData = "";
      ReadFile file = new ReadFile();
      AVLTree<Student> aVLStudent = new AVLTree<Student> ();
      aVLStudent = AccessAVLApp.buildAVL(file);
      Scanner lineScanner = new Scanner (new File("data/oklist.txt"));
      while (lineScanner.hasNextLine()) {
        String[] studentDetails = lineScanner.nextLine().split(" ");
        String studentID = studentDetails[0];
        AccessAVLApp.printStudent(aVLStudent, new Student(studentID));
        Scanner countScanner = new Scanner ( new File ("data/instrumentation/AccessAVLAppFindCount.txt"));
        while (countScanner.hasNextLine()){
          findCountData += countScanner.nextLine() + "\n";
        }
      }
      AccessAVLApp.writeFile("data/experiment/AccessAVLAppFindCount" + args[0] + ".txt", findCountData);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("No args, please specify dataset size.");
    }
  }
}
