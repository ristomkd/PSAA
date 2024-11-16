package Labs;

/* Да се напише функција која за дадена текстуална низа (внесена од тастатура и дадена како аргумент на 
функцијата) ќе направи промена така што ќе ги побара и избрише сите парови 
соседни знаци кои се истата буква од кои едната е голема а другата е мала.*/
import java.util.Scanner;

public class lab2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Vnesi izraz: ");
        String s = input.nextLine(); // sve zema do ENTER, i go smestuva vo string
        System.out.println(izmeni(s));
        
        input.close();
    }

    private static String izmeni(String s) {
        String nov = "";
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1) {
                char c = s.charAt(i);
                char cc = s.charAt(i + 1);
                if (Character.toLowerCase(c) == Character.toLowerCase(cc)){ 
                    i++;
                    continue;
                }
            }
            nov += s.charAt(i);
        }
        return nov;
    }
}
