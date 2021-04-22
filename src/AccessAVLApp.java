import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;

/**
  * Main method that handles command-line arguments, prints all students or single student name.
  * Counts the number of comparison operations done while searching/inserting and outputs to a text file.
  * Uses Student class, ReadFile class and AVL Tree classes (written by Hussein Suleman).
  * @author Ciaran Manca
  */
public class AccessAVLApp {
  public static void main ( String args []) {
    ReadFile file = new ReadFile();
    AVLTree<Student> aVLStudent = new AVLTree<Student> ();
    aVLStudent = buildAVL(file);
    try {
      printStudent(aVLStudent, new Student(args[0]));
    } catch (ArrayIndexOutOfBoundsException e) {
      printAllStudents(aVLStudent);
    }
  }

  /**
   * Takes in ReadFile object, build a String array from ReadFile and uses it to populate AVL Tree.
   * Builds a String of insert operations and writes it to a text file for analysing purposes.
   * Returns an AVL Tree of Student objects
   * @param file ReadFile object for building String array.
   */
  public static AVLTree<Student> buildAVL(ReadFile file) {
    //CreateCountFile("data/instrumentation/AccessAVLAppInsertCount.txt");
    String insertString = "";
    String[][] studentListRaw = file.getList();
    AVLTree<Student> aVLTemp = new AVLTree<Student> ();
    for (int i=0;i<5000;i++) {
      if (studentListRaw[i][0] == null) {
        break;
      }
      String studentNumber = studentListRaw[i][0];
      String name = studentListRaw[i][1];
      aVLTemp.insert(new Student(studentNumber, name));
      insertString += studentNumber + " " + Integer.toString(aVLTemp.getInsertCount()) + "\n";
    }
    writeFile("data/instrumentation/AccessAVLAppInsertCount.txt", insertString);
    return aVLTemp;
  }

  /**
   * Prints matching Student first name and last name for the given student ID in the form of another Student object.
   * Uses the getName method from Student class to print first and last names.
   * Writes the operation counter to output file.
   * @param aVLStudent AVL Tree of Student objects.
   * @param otherStudent A single Student object.
   */
  public static void printStudent(AVLTree<Student> aVLStudent, Student otherStudent) {
    CreateCountFile("data/instrumentation/AccessAVLAppFindCount.txt");
    if (aVLStudent.find(otherStudent) != null) {
      writeFile("data/instrumentation/AccessAVLAppFindCount.txt", otherStudent.getNumber() + " " + Integer.toString(aVLStudent.getFindCount()));
      System.out.println(aVLStudent.find(otherStudent).getData().getName());
    } else {
      System.out.println("Access denied!");
    }
  }

  /**
   * Prints all student ID, first name and last name from the AVL Tree using the inOrder method.
   * @param aVLStudent AVL Tree of Student objects.
   */
  public static void printAllStudents(AVLTree<Student> aVLStudent) {
    aVLStudent.inOrder();
  }

  /**
   * Method for creating new file to write operation counter to.
   * @param filePath String containing the path for the file to be created.
   */
  public static void CreateCountFile(String filePath) {
      File tempFile = new File(filePath);
  }

  /**
   * Method for writing the operation counter to a file.
   * @param filePath String containing the path to the desired file to write to.
   * @param data String to be written to file.
   */
  public static void writeFile(String filePath, String data) {
    try {
      FileWriter fileWriter = new FileWriter(filePath);
      fileWriter.write(data);
      fileWriter.close();
    } catch (IOException e) {
      System.out.println("Error occured.");
    }
  }
}
