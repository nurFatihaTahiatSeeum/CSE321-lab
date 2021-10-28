/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse321lab3;

import java.util.HashMap;
import java.util.*;
/**
 *
 * @author User
 */
class CSE321Lab3 extends Thread {
      int TheUltimateValue = 0;
      static HashMap<Integer, Integer> perThreadMaxDivisor = new HashMap<Integer, Integer>();


    int min, max;
     static int ThreadCount = 0;
        public CSE321Lab3(int start, int end) {
          this.min = start;
          this.max = end;
        }
        
        public void run() {
          int LargestDiivisor = 0;
          int LargestDiivisorPointer = 0;
          for (int i = min; i < max; i++) {
              int divisorNumber = Task2.DivesorForEachInteger(i);
              if (divisorNumber > LargestDiivisor) {
                LargestDiivisor = divisorNumber;
                LargestDiivisorPointer = i;
              }
          }
          perThreadMaxDivisor.put(LargestDiivisorPointer, LargestDiivisor);
//          System.out.println(ThreadCount);
//          System.out.println(perThreadMaxDivisor);
          
          if(ThreadCount == 9){
                TheUltimateValue = sortmapKey.sortbykey(perThreadMaxDivisor);
              System.out.println("The integer in the range 1 to 100000 that has the largest number of divisors is: "  +TheUltimateValue);
          }
          ThreadCount++;
        }

}
 class sortmapKey {
    
   
    static int[] p = new int[10]; 
    static List<Integer> keys = new ArrayList<Integer>();

    public static int sortbykey( Map<Integer, Integer> map) 
    { 
        TreeMap<Integer, Integer> AfterSort = new TreeMap<>(); 
        AfterSort.putAll(map); 

        for (Map.Entry<Integer, Integer> entry : AfterSort.entrySet()) { 
            keys.add(entry.getKey());
        }
         return keys.get(keys.size()-1);
    } 
      
}
public class Task2 {
    int[] perThreadMaxDivisorCount = new int[10];
   int[] perThreadMaxDivisorNum = new int[10];
   static HashMap<Integer, Integer> perThreadMaxDivisor = new HashMap<Integer, Integer>();

  int NumberOfThreads = 10;

    public static void ThreadCreate_DivisorCount(int numberOfThreads) {
      CSE321Lab3[] threadArr = new CSE321Lab3[numberOfThreads];

      for (int i = 0, start = 1,end = start + 10000; i < numberOfThreads; i++,start+=10000, end+=10000) {
        threadArr[i] =  new CSE321Lab3(start, end);
      }
      // int LargestDiivisor = 0;
      for (int i = 0; i < numberOfThreads; i++){
        threadArr[i].start();
      }
          
    }
    public static int DivesorForEachInteger(int N) {
        int count = 0;
        for (int i = 1; i <= N ; i++) {
          if ( N % i == 0 )
              count ++;
        }
        return count;
    }
    // public static void StoringValues() {
    //      perThreadMaxDivisor = FindDivisors
    //   // perThreadMaxDivisorNum = FindDivisors.getDivisorPinter();
    //      System.out.println(perThreadMaxDivisor);
    // }

    public static void main(String[] args) {
      int numberOfThreads = 10;
      int TheUltimateValue = 0;

      ThreadCreate_DivisorCount(numberOfThreads);
//       CSE321Lab3.getDivisorCount();

    }
}
