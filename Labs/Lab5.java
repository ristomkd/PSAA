package lab5;
import java.util.Scanner;
import java.util.Stack;

/* Да се напише програма во која корисникот од тастатура внесува стринг кој претставува сегмент од HTML код. Со помош на магацин да се провери дали кодот е валиден, т.е. дали секој отворен таг има соодветен затворен таг и валидна содржина. */
public class Lab5 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Vnesi izraz: ");
        String html = input.nextLine();
        Stack<String> stack = new Stack<String>();
        boolean validen = true;

        String[] niza = html.split("<"); //["", "h1>", "a>", "p>Test", "/p>", "/a>", "/h1>"]

        for(String part : niza) {
            if(part.trim().isEmpty()) continue;
            
            String tag = otstrani(part).trim(); //posle otstrani "p " mora trim za da nema prazni mesta
            /* 
               1. "h1"
               2. "a"
               3. "p"
               4. "/p"
               5. "/a"
               6. "/h1"
            */
            if(tag.isEmpty()) continue;
            
            System.out.println("Najden tag: " + tag); // Za debugiranje
            
            if(tag.startsWith("/")) { // Ako e zatvoren tag
                String closeTag = tag.substring(1); // go trgame '/'
                if(!stack.isEmpty()) {
                    String lastTag = stack.pop();
                    System.out.println("Sporeduvame: " + lastTag + " so " + closeTag); // Za debugiranje
                    if(!lastTag.equals(closeTag)) {
                        validen = false;
                        System.out.println("Greshka: Tagot " + closeTag + " ne odgovara na " + lastTag);
                        break;
                    }
                } else {
                    validen = false;
                    System.out.println("Greshka: Najden e zatvoren tag bez otvoren tag");
                    break;
                }
            } else {
                stack.push(tag);
                System.out.println("Dodaden na magacin: " + tag); // Za debugiranje
            }
        }
        
        if(!stack.isEmpty()) { //mora stackot na kraj da e prazen, za da e tocho, pri sporedba se vadi tagot, i treba na kraj prazno
            //dokolku ima <h1></h1> se vadat i e prazen, no dokolku <h1><h1> dvata se smestuvaat i na kraj gledame deka ne e prazen so znaci
            //greska vo tagovi (NEVALIDNI TAGOVI)
            validen = false;
            System.out.println("Greshka: Ima nezatvoreni tagovi: " + stack);
        }
        
        if(validen) {
            System.out.println("HTML kodot e validen!");
        }
        
        input.close();
    }

    private static String otstrani(String part) {
    String result="";
    for(int i=0;i<part.length();i++){
        if(part.charAt(i)=='>'){
            break;
        }
        result += part.charAt(i);
    }
    return result;
    }
    
}

//<h1><a><p>Test</p></a></h1>