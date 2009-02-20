/**
 * 
 * @author Brian Timm
 * 
 */

public class Student implements Comparable<Student> {
   public static int id = 1;
   int idNum;
   int HLQuotient;
   String first;

   public Student(String firstName) {
      idNum = id;
      id++;
      first = firstName;
      HLQuotient = 0;
   }
   
   public void setHLQ (int HLQ){
      HLQuotient = HLQ;
   }

   public int compareTo(Student other) {
      if (this.HLQuotient == other.HLQuotient) {
         return 0;
      } else if (this.HLQuotient < other.HLQuotient){
         return -1;
      } else {
         return 1;
      }
   }

   public String toString() {
      return "{ id: " + id + ", name: " + first + " }";
   } 
}
