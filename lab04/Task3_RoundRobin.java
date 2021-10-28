package cse321_assignent5;

import java.util.*;

public class Task3_RoundRobin {

    static HashMap<Integer, Integer> ArrTimeBurstTime = new HashMap<Integer, Integer>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter The Number Of Processes!");
        int n = sc.nextInt();
        int ProcessID[] = new int[n];
        int BurstTime[] = new int[n];
        int completionTime[] = new int[n];
        int response_time[] = new int[n];
        int TurnAraound_time[] = new int[n];
        int waiting_time[] = new int[n];
        int ArraivalTime[] = new int[n];
        int ArraivalTimeCopy[] = new int[n];
        int BurstTimeCopy[] = new int[n];
        int Remaining_Time[] = new int[n];
        int flag[] = new int[n];
        int idx[] = new int[n];

        int TimeQuantum = 4;
        int current_Time = 0;
        int StartTime = 0;
        int CompletedProcess = 0;
        int remainingProcess = n;
        double avgWaitingTime = 0;
        double avgturnAroundTime = 0;

        for (int i = 0; i < n; i++) {

            ProcessID[i] = i + 1;
            System.out.println("enter process " + (i + 1) + " burst time:");
            BurstTime[i] = sc.nextInt();
            Remaining_Time[i] = BurstTime[i];
            ArraivalTime[i] = 0;
            flag[i] = 0;

        }

        while (CompletedProcess != n) {

            for (int i = 0; i < n || CompletedProcess < n; i = i % n) {
                if (Remaining_Time[i] > 0) {
                    if (Remaining_Time[i] > TimeQuantum) {
                        
                        if (remainingProcess == 1) {
                           
                            current_Time = current_Time + Remaining_Time[i] + 1;
                            Remaining_Time[i] = 0;
                            completionTime[i] = current_Time;
                            CompletedProcess++;
                            remainingProcess--;
                            break;
                            
                        }
                        current_Time = current_Time + TimeQuantum;
                        Remaining_Time[i] = Remaining_Time[i] - TimeQuantum;
                        i++;
                    } else {
//                         System.out.println("current process " + i);
                        current_Time = current_Time + Remaining_Time[i];
                        Remaining_Time[i] = 0;
                        completionTime[i] = current_Time;
                        CompletedProcess++;
                        remainingProcess--;
//                        System.out.println("current_Time " + current_Time);
//                        System.out.println("Completed Processes Number: " + CompletedProcess);
                        flag[i] = 1;
                        i++;

                    }
                }
            }
            for (int i = 0; i < n; i++) {
                TurnAraound_time[i] = completionTime[i] - ArraivalTime[i];
                waiting_time[i] = TurnAraound_time[i] - BurstTime[i];
            }

            for (int i = 0; i < n; i++) {

                avgWaitingTime += waiting_time[i];
                avgturnAroundTime += TurnAraound_time[i];
            }
            System.out.println("Completion Time(CT)   Turnaround Time(TAT)  Waiting Time(WT)");
            for (int i = 0; i < n; i++) {
                System.out.println(completionTime[i] + "\t\t\t" + TurnAraound_time[i] + "\t\t\t" + waiting_time[i]);
            }
            System.out.println("\nAverage Turnaround Time is " + (float) (avgturnAroundTime / n));
            System.out.println("Average Waiting Time is " + (float) (avgWaitingTime / n));
            sc.close();

        }
    }
}
