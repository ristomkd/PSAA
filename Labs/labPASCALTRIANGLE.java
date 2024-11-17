package Labs;
import java.util.Scanner;
public class labPASCALTRIANGLE {
    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        System.out.println("Vnesi N: ");
        int n=s.nextInt();
        pascal(n);
                s.close();
        }
        
    private static void pascal(int n) {
        int[][] matrix = new int[n][];
        for(int i=0;i<n;i++){
            matrix[i]=new int[i+1]; //red i ke ima i+1 elementi

            matrix[i][0]=1;//prvite 
            matrix[i][i]=1;//i poslednite sekogash se 1 (dijagonala)

            for(int j=1;j<i;j++){ //od 1 deka prviot red so prvata kola se popolneti
                matrix[i][j] = matrix[i-1][j-1]+matrix[i-1][j];
            }   
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}
