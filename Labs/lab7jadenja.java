package Labs;

import java.util.Arrays;
import java.util.Scanner;

public class lab7jadenja {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vnesete go brojot na jadenja: ");
        int n = scanner.nextInt();
        
        int[] preparationTimes = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Vnesi vreme za podgotovka na jadenje " + (i+1) + ": ");
            preparationTimes[i] = scanner.nextInt();
        }
        
        scanner.close();
        // Сортирање во растечки редослед (важно за минимален број на поени)
        Arrays.sort(preparationTimes);
        
        int currentTime = 0;
        int totalPenalty = 0;
        
        System.out.println("\nPresmetka na kazneni poeni:");
        for (int i = 0; i < preparationTimes.length; i++) {
            currentTime += preparationTimes[i];
            totalPenalty += currentTime;
            System.out.println("Po jadenje " + (i+1) + " (" + preparationTimes[i] + " min):");
            System.out.println("- Vkupno izminato vreme: " + currentTime);
            System.out.println("- Kazneni poeni do sega: " + totalPenalty);
        }
        
        System.out.println("\nMinimalen broj na kazneni poeni: " + totalPenalty);
    }
}