import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class Experiment {
  public static void main (String args[]) throws FileNotFoundException {
    try {
      //AccessAVLApp.CreateCountFile("data/experiment/AccessAVLAppFind" + args[0]);
      //AccessAVLApp.CreateCountFile("data/experiment/AccessAVLAppInsert" + args[0]);
      String findCountData = "";
      ReadFile file = new ReadFile();
      AVLTree<Student> aVLStudent = new AVLTree<Student> ();
      aVLStudent = AccessAVLApp.buildAVL(file);
      // store insert count
      AccessAVLApp.writeFile("data/experiment/AccessAVLAppInsertCount" + args[0], Integer.toString(aVLStudent.getInsertCount()));
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
      AccessAVLApp.writeFile("data/experiment/AccessAVLAppFindCount" + args[0], findCountData);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("No args, please specify dataset size.");
    }
  }

  //public static void append(String appendData, String path) throws IOException {
  //  Path filePath = Paths.get(path);
  //  Files.write(filePath, appendData.getBytes(), StandardOpenOption.APPEND);
  //}
}
