import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SeatingChart {

   public static void main(String[] args) throws FileNotFoundException {
      int tableSize;
      int numStudents;
      int numTables;
      BinHeap<Student> stdntQ = new BinHeap<Student>();
      Scanner input = new Scanner(System.in);
      System.out.println("Welcome to the Seat Chart Calculator v 0.9a!");
      System.out.println("How many seats at a table?");
      tableSize = input.nextInt();
      System.out.println("How many students in your class?");
      numStudents = input.nextInt();
      Student[] stdnts = new Student[numStudents];
      SolutionQueue solns = new SolutionQueue(numStudents);
      numTables = numStudents / tableSize;
      if (numStudents % tableSize != 0) {
         numTables++;
      }
      System.out
            .println("What file contains your student seating preference data?");
      String filename = input.next();
      File stdntData = new File(filename);
      Scanner fileScan = new Scanner(stdntData);
      int curStdnt = 0;
      while (fileScan.hasNext()) {
         Scanner lineScan = new Scanner(fileScan.nextLine());
         while (lineScan.hasNext()) {
            String stdntName = lineScan.next();
            if (!stdntName.equalsIgnoreCase("l")
                  && !stdntName.equalsIgnoreCase("d")) {
               Student temp = new Student(stdntName);
               stdnts[temp.idNum - 1] = temp;
               curStdnt = temp.idNum;
            } else if (stdntName.equalsIgnoreCase("l")) {
               int stdntNum = lineScan.nextInt();
               solns.setHLM(stdntNum - 1, curStdnt - 1, 1);
            } else {
               int stdntNum = lineScan.nextInt();
               solns.setHLM(stdntNum - 1, curStdnt - 1, -1);
            }
         }
      }
      // get HLQ's for the students and put them in the student Queue
      for (int i = 0; i < stdnts.length; i++){
         stdnts[i].setHLQ(solns.getHLQ(i));
         stdntQ.insert(stdnts[i]);
      }
      
      // start creating solutions until all solutions contain all students
      if (solns.isEmpty()){
         Student[][] seatCht = new Student[numTables][tableSize];
         for (int i = 0; i < seatCht.length; i++){
            
         }
      }
   }
}
