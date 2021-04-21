import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;


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

  public static AVLTree<Student> buildAVL(ReadFile file) {
    CreateCountFile("data/instrumentation/AccessAVLAppInsertCount.txt");
    String[][] studentListRaw = file.getList();
    AVLTree<Student> aVLTemp = new AVLTree<Student> ();
    for (int i=0;i<5000;i++) {
      if (studentListRaw[i][0] == null) {
        break;
      }
      String studentNumber = studentListRaw[i][0];
      String name = studentListRaw[i][1];
      aVLTemp.insert(new Student(studentNumber, name));
      WriteFile("data/instrumentation/AccessAVLAppInsertCount.txt", Integer.toString(aVLTemp.getInsertCount()));
    }
    return aVLTemp;
  }

  public static void printStudent(AVLTree<Student> aVLStudent, Student otherStudent) {
    CreateCountFile("data/instrumentation/AccessAVLAppFindCount.txt");
    if (aVLStudent.find(otherStudent) != null) {
      WriteFile("data/instrumentation/AccessAVLAppFindCount.txt", Integer.toString(aVLStudent.getFindCount()));
      System.out.println(aVLStudent.find(otherStudent).getData().getName());
    } else {
      System.out.println("Access denied!");
    }
  }

  public static void printAllStudents(AVLTree<Student> aVLStudent) {
    aVLStudent.inOrder();
  }

  public static void CreateCountFile(String filePath) {
      File tempFile = new File(filePath);
  }

  public static void WriteFile(String filePath, String data) {
    try {
      FileWriter fileWriter = new FileWriter(filePath);
      fileWriter.write(data);
      fileWriter.close();
    } catch (IOException e) {
      System.out.println("Error occured.");
    }
  }
}
