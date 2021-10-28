package cse321_assignent5;

import java.util.Scanner;

public class Task2_PriorityQ {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter The Number Of Processes!");
        int n = sc.nextInt();
        int ProcessID[] = new int[n];
        int Priority[] = new int[n];
        int BurstTime[] = new int[n];
        int complete_time[] = new int[n];
        int response_time[] = new int[n];
        int TurnAraound_time[] = new int[n];
        int waiting_time[] = new int[n];
        int ArraivalTime[] = new int[n];
        int BTTrack[] = new int[n];
        int flag[] = new int[n];
        int priorityCount = 1;
        int start_Time = 0;
        int current_Time = 0;
        int CompletedProcess = 0;
        float avgWaitingTime = 0, avgturnAroundTime = 0;

        for (int i = 0; i < n; i++) {
            // Take file input 
            ProcessID[i] = i + 1;
            System.out.println("enter process " + (i + 1) + " burst time:");
            BurstTime[i] = sc.nextInt();
            BTTrack[i] =BurstTime[i];
            System.out.println("enter process " + (i + 1) + " Priority level:");
            Priority[i] = sc.nextInt();
            flag[i] = 0;
            ArraivalTime[i] = 0;

        }

        while (CompletedProcess != n) {
            int c = n;
            int min = 999;
//            if (CompletedProcess == n) {
//                break;
//            }
            for (int i = 0; i < n; i++) {
                if (ArraivalTime[i] <= start_Time && flag[i] == 0 && Priority[i] == priorityCount && BurstTime[i] < min) {
//                    System.out.println("Found the Process with priority Count "+priorityCount);
                    min = BurstTime[i];
                    c = i;                   
                    break;
                }
            }
            if (c != n && min != 999) {
                if(flag[c] !=0){
                    start_Time = current_Time;
                }
                BTTrack[c]--;
                current_Time++;
                if (BTTrack[c] == 0) {
                    complete_time[c] = current_Time;
                    TurnAraound_time[c] = complete_time[c] - ArraivalTime[c];
                    waiting_time[c] = TurnAraound_time[c] - BurstTime[c];
                    response_time[c] = start_Time - ArraivalTime[c];
                    flag[c] = 1;
                    CompletedProcess++;
                    priorityCount++;
                }
            }
            else{
                    current_Time++;               
            }
        }
        for (int i = 0; i < n; i++) {

            avgWaitingTime += waiting_time[i];
            avgturnAroundTime += TurnAraound_time[i];
        }
        System.out.println("Completion Time(CT)   Turnaround Time(TAT)  Waiting Time(WT)");
        for (int i = 0; i < n; i++) {
            System.out.println(complete_time[i] + "\t\t\t" + TurnAraound_time[i] + "\t\t\t" + waiting_time[i]);
        }
        System.out.println("\nAverage Turnaround Time is " + (float) (avgturnAroundTime / n));
        System.out.println("Average Waiting Time is " + (float) (avgWaitingTime / n));
        sc.close();

    }
}
