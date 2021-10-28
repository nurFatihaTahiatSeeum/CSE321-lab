package cse321_assignent5;

import java.util.Scanner;

public class Task1_SJF {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter The Number Of Processes!");
        int n = sc.nextInt();
        int ProcessID[] = new int[n];
        int ArraivalTime[] = new int[n];
        int BurstTime[] = new int[n];
        int complete_time[] = new int[n];
        int TurnAraound_time[] = new int[n];
        int waiting_time[] = new int[n];
        int BTTrack[] = new int[n];
        int flag[] = new int[n];
        int start_Time = 0;
        int CompletedProcess = 0;
        float avgWaitingTime = 0, avgturnAroundTime = 0;

        for (int i = 0; i < n; i++) {
            // Take file input 
            ProcessID[i] = i + 1;
            System.out.println("enter process " + (i + 1) + " arrival time:");
            ArraivalTime[i] = sc.nextInt();
            System.out.println("enter process " + (i + 1) + " burst time:");
            BurstTime[i] = sc.nextInt();
            BTTrack[i] = BurstTime[i];
            flag[i] = 0;

        }

        boolean a = true;
        while (true) {
            int c = n;
            int min = 999;//dummy value
            if (CompletedProcess == n)
            {
                break;
            }
            for (int i = 0; i < n; i++) {
                if (ArraivalTime[i] <= start_Time && flag[i] == 0 && BurstTime[i] < min) {
                    min = BurstTime[i];
                    c = i;
                }
            }
            if (c == n) {
                start_Time++;
            } else {
                BTTrack[c]--;
                start_Time++;
                if (BTTrack[c] == 0) {
                    complete_time[c] = start_Time;
                    flag[c] = 1;
                    CompletedProcess++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            TurnAraound_time[i] = complete_time[i] - ArraivalTime[i];
            waiting_time[i] = TurnAraound_time[i] - BurstTime[i];
            avgWaitingTime += waiting_time[i];
            avgturnAroundTime += TurnAraound_time[i];
        }
        System.out.println("Completion Time(CT)   Turnaround Time(TAT)  Waiting Time(WT)");
        for (int i = 0; i < n; i++) {
            System.out.println( complete_time[i] + "\t\t\t" + TurnAraound_time[i] + "\t\t\t" + waiting_time[i]);
        }
        System.out.println("\nAverage Turnaround Time is " + (float) (avgturnAroundTime / n));
        System.out.println("Average Waiting Time is " + (float) (avgWaitingTime / n));
        sc.close();

    }
}
