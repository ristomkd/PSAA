package Ispit09_04_2024;
import java.util.Stack;

// razlicna verzija mlc :)

/* Да се напише функција која како аргумент добива магацин. Во магацинот се сместени топчиња
со различни четири тежини, нумерирани од 1 до 4. Топчиња со поголема тежина можат да ги
поништат сите топчиња со помала тежина кои се наоѓаат под нив во магацинот. Дополнително,
две топчиња со иста тежина кои се наоѓаат едно до друго можат да се здружат во едно топче
со тежина 5. Две топчиња со тежина 5 не можат да се здружат во едно топче. Топче со тежина
5 може да поништи само две топчиња со строго помала тежина кои се директно под него во
магацинот. Функцијата треба да го врати променетиот магацин.
Да се напише главна програма во која ќе се внесе еден магацин и во која ќе се повика и тестира
работата на функцијата.  */

/* [3, 2, 1, 1, 2, 3, 3, 4, 2, 3, 1] */ /* 1i1 edno do drugo pravat 5 */
/* [3, 2, 5, 2, 3, 3, 4, 2, 3, 1]  */   /* 5 ponistuva pomali pred nego */
/* [5, 2, 3, 3, 4, 2, 3, 1] */
/* [5, 2, 5, 4, 2, 3, 1] */
/* [5, 5, 4, 2, 3, 1]  */ /* topceto 3 go ponistuva 2 */
/* [5, 5, 4, 3, 1] */
public class vtorazadaca {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        stack.push(3);
        stack.push(2);
        stack.push(4);
        stack.push(3);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        // za test stack.pop();
        izmeni(stack);
        
    }
        
    private static void izmeni(Stack<Integer> stack) {
        Stack<Integer> pomStack = new Stack<>();
        while(!stack.isEmpty()){
            int tmp = stack.pop();
            if(stack.isEmpty()){
                pomStack.push(tmp);
                break;
            }
            int sleden = stack.peek();
            if(tmp==5){
                if( sleden!=5){
                    stack.pop();
                }
                int sleden1=stack.peek();
                if(sleden1!=5){
                    stack.pop();
                }
            }
            if(tmp==sleden && tmp!=5){
                stack.pop(); // dokolku se ednakvi vadime dvata i stavame 5
                stack.push(5);
            } else {
                pomStack.push(tmp);
            }

        }
        while(!pomStack.isEmpty()){
            stack.push(pomStack.pop());
        }
        System.out.print(stack);
    }
}
