/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse321lab3;

/**
 *
 * @author User
 */
class MyThread extends Thread{
   static int NumberCount;
   static String name;

  public MyThread(int NumCount, String name){
    NumberCount = NumCount;
    currentThread().setName(name);
  }
  public static int getcount(){
    return NumberCount;
  }

  public void run(){
//    System.out.println(Thread.currentThread().getName());
    for(int i=0; i<10; i++){
      NumberCount++;
      System.out.println(NumberCount);
    }
  }

}

public class Task1 {
    static int NumberCount = 0;
  public static void main(String[] args) {
    MyThread myThread1 = new MyThread(NumberCount,"myThread01 ");
    myThread1.run();

    NumberCount = MyThread.getcount();
    // System.out.println(NumberCount);

    MyThread myThread2 = new MyThread(NumberCount,"myThread02");
    myThread2.run(); 
    myThread1.run(); 
  }
}
