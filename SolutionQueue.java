public class SolutionQueue {

   private class Solution implements Comparable<Solution> {
      Student[][] seatChart;
      boolean[] happyKidIndex;
      double happyPercent; // the percent of happy kids in this solution.
      int seated; // the number of students currently seated in this solution.

      public Solution(Student[][] seatCht) {
         seatChart = seatCht;
         seated = calcNumKids(seatChart);
         happyKidIndex = calcHKI(seatChart, seated);
         happyPercent = calcHappy(happyKidIndex) / seated;
      }

      public int compareTo(Solution other) {
         if (this.happyPercent == other.happyPercent) {
            return 0;
         } else if (this.happyPercent > other.happyPercent) {
            return -1;
         } else {
            return 1;
         }
      }
   }

   private int[][] HLMatrix;

   private BinHeap<Solution> solns;

   public SolutionQueue(int numKids) {
      HLMatrix = new int[numKids][numKids];
      solns = new BinHeap<Solution>();
   }

   public void add(Solution soln) {
      if (soln.happyPercent >= 0) {
         solns.insert(soln);
      }
   }

   public Solution remove() {
      return solns.deleteMin();
   }

   private int calcHappy(boolean[] HKI) {
      int numHappy = 0;
      for (int i = 0; i < HKI.length; i++) {
         if (HKI[i]) {
            numHappy++;
         }
      }
      return numHappy;
   }

   private boolean[] calcHKI(Student[][] soln, int numKids) {
      boolean[] HKI = new boolean[numKids + 1];
      for (int i = 0; i < soln.length; i++) {
         for (int j = 0; soln[i][j].id != 0; j++) {
            for (int k = j + 1; soln[i][k].id != 0; k++) {
               int AlikesB = HLMatrix[soln[i][j].id][soln[i][k].id];
               int BlikesA = HLMatrix[soln[i][k].id][soln[i][j].id];
               int stdntA = soln[i][j].id;
               int stdntB = soln[i][k].id;
               if (AlikesB == 1) {
                  HKI[stdntA] = true;
               } else if (AlikesB == -1) {
                  HKI[0] = true; // flag for an unhappy child
                  HKI[stdntA] = false;
               } else {
                  HKI[stdntA] = false;
               }
               if (BlikesA == 1) {
                  HKI[stdntB] = true;
               } else if (BlikesA == -1) {
                  HKI[0] = true; // flag for an unhappy child
                  HKI[stdntB] = false;
               } else {
                  HKI[stdntB] = false;
               }
            }
         }
      }
      return HKI;
   }

   private int calcNumKids(Student[][] soln) {
      int kids = 0;
      for (int i = 0; i < soln.length; i++) {
         for (int j = 0; soln[i][j].id != 0; j++) {
            kids++;
         }
      }
      return kids;
   }
   
   public void setHLM(int id1, int id2, int HL){
      HLMatrix[id1][id2] = HL;
   }
   
   public int getHLQ(int id){
      int ret = 0;
      for(int i = 0; i < HLMatrix[id].length; i++){
         ret += HLMatrix[id][i];
      }
      return ret;
   }
   
   public boolean isEmpty(){
      return solns.isEmpty();
   }
}
