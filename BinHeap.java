/**
 * BinHeap - This class defines a minimum heap that uses the Comparable
 * interface to sort the elements
 * 
 * @author Brian Timm
 */

public class BinHeap<T extends Comparable<? super T>> {
   public static class MyException extends RuntimeException {
      public MyException() {
         super();
      }

      public MyException(String message) {
         super(message);
      }
   }

   private T[] heap;
   private int size;

   public BinHeap() {
      heap = (T[]) new Comparable[100];
      size = 0;
   }

   public BinHeap(int initSize) {
      heap = (T[]) new Comparable[initSize];
      size = 0;
   }
   
   public void insert(T item) {
      if (size == heap.length) {
         T[] temp = (T[]) new Comparable[heap.length * 2];
         System.arraycopy(heap, 0, temp, 0, heap.length);
         heap = temp;
      }
      // Adds the item to the end, then moves it up until it reaches it's proper
      // place in the heap
      int hole = size;
      int parent = (hole - 1) / 2;
      while (hole > 0 && item.compareTo(heap[parent]) < 0) {
         heap[hole] = heap[parent];
         hole = parent;
         parent = (hole - 1) / 2;
      }
      heap[hole] = item;
      size++;
   }

   public T deleteMin() {
      if (size == 0) {
         throw new MyException();
      }
      T ret = heap[0];
      T item = heap[size - 1];
      size--;
      int hole = 0;
      int newhole = newHole(hole, item);
      while (newhole > 0 && newhole <= size - 1) {
         heap[hole] = heap[newhole];
         hole = newhole;
         newhole = newHole(hole, item);
      }
      heap[hole] = item;
      return ret;
   }

   private int newHole(int hole, T item) {
      int leftChild = 2 * hole + 1;
      int rightChild = 2 * hole + 2;
      int newhole = -1;
      boolean hasLeftChild = leftChild < size;
      boolean hasRightChild = rightChild < size;
      if (hasLeftChild) {
         if (!hasRightChild) {
            if (item.compareTo(heap[leftChild]) > 0) {
               newhole = leftChild;
            }
         } else {
            int smallChild;
            if (heap[leftChild].compareTo(heap[rightChild]) < 0) {
               smallChild = leftChild;
            } else {
               smallChild = rightChild;
            }
            if (item.compareTo(heap[smallChild]) > 0) {
               newhole = smallChild;
            }
         }
      }
      return newhole;
   }

   public boolean isEmpty() {
      return size == 0;
   }

   public int size() {
      return size;
   }

   public void print() {
      for (int i = 0; i < size; i++) {
         System.out.print(heap[i] + " ");
      }
      System.out.println();
   }
}
