package Labs;
import java.util.*;

public class lab9ruler {
    public static void main(String[] args) {
        int n = 3;
        ruler(n);
    }
    public static void printDashes(int n){
        for(int i=0;i<n;i++){
            System.out.print("-");
        }
        System.out.println();
    }
    public static void sredinka(int n){
        if(n==1){
            System.out.println("-");
        return;}
        sredinka(n-1);
        printDashes(n);
        sredinka(n-1);
    }
    public static void ruler(int n){
        for(int i=0;i<n-1;i++){
            printDashes(n);
            sredinka(n-1);
        }
        printDashes(n);
    }
} 
    

