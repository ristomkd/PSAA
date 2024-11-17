package Labs;

import java.util.Arrays;
import java.util.Scanner;

public class lab7spisanjeTESHKA {
   {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vnesete go brojot na jadenja: ");
        int n = scanner.nextInt();
        
        int[] preparationTimes = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Vnesi vreme za podgotovka na jadenje " + (i+1) + ": ");
            preparationTimes[i] = scanner.nextInt();
        }
        
        // Сортирање во опаѓачки редослед
        Arrays.sort(preparationTimes);
        int[] sortedDesc = new int[n];
        for(int i = 0; i < n; i++) {
            sortedDesc[i] = preparationTimes[n-1-i];
        }
        
        // Време на завршување за секое јадење
        int[] finishTimes = new int[n];
        // Го поставуваме првото (најдолгото) јадење
        finishTimes[0] = sortedDesc[0];
        
        // За секое следно јадење
        for(int i = 1; i < n; i++) {
            // Го наоѓаме најраниот можен почеток
            int earliestStart = 0;
            for(int j = 0; j < i; j++) {
                if (finishTimes[j] - sortedDesc[i] >= earliestStart) {
                    earliestStart = 0;
                } else {
                    earliestStart = Math.max(earliestStart, sortedDesc[i] - (finishTimes[j] - earliestStart));
                }
            }
            finishTimes[i] = earliestStart + sortedDesc[i];
        }
        
        // Пресметување на вкупните казнени поени
        int totalPenalty = 0;
        for(int finishTime : finishTimes) {
            totalPenalty += finishTime;
        }
        
        System.out.println("Vreminja na zavrshuvanje na jadenjata:");
        for(int i = 0; i < n; i++) {
            System.out.println("Jadenje " + (i+1) + ": " + finishTimes[i]);
        }
        System.out.println("Vkupno kazneni poeni: " + totalPenalty);
    } 
}
