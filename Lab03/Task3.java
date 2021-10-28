/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse321lab3;

import java.util.*;

class DivideConquer extends Thread {

    int start = 0;
    int end = 0;
    int[] DevidedArr;

    DivideConquer(int[] Arr, int start, int end) {
        DevidedArr = Arrays.copyOfRange(Arr, start, end + 1);
//        System.out.println("//starts from " + start);
//        for (int i = 0; i < DevidedArr.length; i++) {
//            System.out.println(DevidedArr[i]);
//        }
    }

    public static int[] Result(int[] part1, int[] part2) {
        int[] MergerdArray = new int[part1.length + part2.length];
//        System.out.println("//printing part1 of length " + part1.length);
//        for (int i = 0; i < part1.length; i++) {
//            System.out.println(part1[i]);
//        }
//        System.out.println("//printing part2 of length " + part2.length);
//        for (int i = 0; i < part2.length; i++) {
//            System.out.println(part1[i]);
//        }
//        System.out.println("//making MergerdArray of length " + MergerdArray.length);
        for (int i = 0, j = 0, k = 0; (i < part1.length && j < part2.length); i = i, j = j, k = k) {
            if (part1[i] <= part2[j]) {
                MergerdArray[k] = part1[i];
                i++;
                k++;
            } else {
                MergerdArray[k] = part2[j];
                j++;
                k++;
            }
            if (i == part1.length) {
                while (j < part2.length) {
                    MergerdArray[k] = part2[j];
                    k++;
                    j++;
                }
            }
            if (j == part2.length) {
                while (i < part1.length) {
                    MergerdArray[k] = part1[i];
                    k++;
                    i++;
                }
            }
        }
        return MergerdArray;
    }

    public void Merge(int[] DevidedArr, int[] left, int[] right) {
        int LeftPointer = 0;
        int RightPointer = 0;

        for (int i = 0; i < DevidedArr.length; i++) {
            if (RightPointer >= right.length || (LeftPointer < left.length && left[LeftPointer] <= right[RightPointer])) {
                DevidedArr[i] = left[LeftPointer];
                LeftPointer++;
            } else {
                DevidedArr[i] = right[RightPointer];
                RightPointer++;
            }
        }
    }

    public void mergesort(int[] DevidedArr) {

        if (DevidedArr.length > 1) {
            int leftHalfLength = DevidedArr.length / 2;
            int RightHalfLength = DevidedArr.length - leftHalfLength;

            int[] left = new int[leftHalfLength];
//            System.arraycopy(DevidedArr, 0, left, 0, leftHalfLength);
             for (int i = 0; i < leftHalfLength; i++) {
                left[i] = DevidedArr[i] ;
            }

            int[] right = new int[RightHalfLength];
             for (int i = 0; i < RightHalfLength; i++) {
                right[i] = DevidedArr[i + leftHalfLength] ;
            }
             
//            System.arraycopy(DevidedArr, leftHalfLength, right, 0, RightHalfLength);

            mergesort(left);
            mergesort(right);

            Merge(DevidedArr, left, right);
        }
    }

    @Override
    public void run() {
        mergesort(DevidedArr);
    }

    public int[] sortedhalf() {
        return DevidedArr;
    }
}

/**
 *
 * @author User
 */
public class Task3 {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Enter The length Of your Array!");
        int length = Sc.nextInt();
        if (length <= 2) {
            System.out.println("Already Sorted!");
        } else {
            int[] Arr = new int[length];
            for (int i = 0; i < length; i++) {
                Arr[i] = Sc.nextInt();
            }
            DivideConquer[] threadArr = new DivideConquer[2];
            threadArr[0] = new DivideConquer(Arr, 0, (Arr.length / 2) - 1);
            threadArr[1] = new DivideConquer(Arr, (Arr.length / 2), Arr.length - 1);
            threadArr[0].run();
            threadArr[1].run();
            int[] MergerdArray = DivideConquer.Result(threadArr[0].sortedhalf(), threadArr[1].sortedhalf());
            System.out.println("//Printing Sorted Array" );
            for (int i = 0; i < MergerdArray.length; i++) {
                System.out.println(MergerdArray[i]);
            }
        }
    }

}
