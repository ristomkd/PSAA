package Labs;

import java.util.Scanner;

public class lab3 {

    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        System.out.println("Vnesi dimenzija na matricata:");
        int size = input.nextInt();
        input.nextLine(); // za Enter
        int [][]matrix = new int[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.print("Enter matrix at position ["+i+"]["+j+"]");
                int value = input.nextInt();
                
                // Проверка за валиден опсег на вредности
                if (value < 0 || value > 255) {
                    System.out.println("Vrednostite mora da bidat pomegju 0 i 255!");
                    input.close();
                    return;
                }
                matrix[i][j] = value;
            }
        }
        rotirajmatrica(matrix);
        
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        
        input.close();
        }

    private static void rotirajmatrica(int[][] matrix) {
        int n = matrix.length;
        
        // Прво транспонирање на матрицата
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
        // Потоа превртување на редиците
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
        /*for(int j=0;j<n;j++){   DOKOLKU SAKAME ROTACIJA 90 LEVO
            for(int i=0;i<n/2;i++){
                int temp=matrix[i][j];
                matrix[i][j]=matrix[n-1-i][j];
                matrix[n-1-i][j]=temp;
            }
        } */

        /*
        int[][] rotated= new int[n][n]; SO VOVEDUVANJE NA NOVA MATRICA ROTACIJA 90 DESNO
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                rototed[j][n-1-i]=matrix[i][j];
            }
        }
         */

     /* int[][] rotated= new int[n][n]; SO VOVEDUVANJE NA NOVA MATRICA ROTACIJA 90 LEVO
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                rototed[n-1-j][i]=matrix[i][j];
            }
        }
         */
    }
}

