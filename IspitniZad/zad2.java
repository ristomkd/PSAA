package IspitniZad;
import java.util.ArrayList;
import java.util.Stack;
/*Со додавање на чинии една врз друга се формира магацин. Ако магацинот стане превисок,
истиот може да се преврти. 
За таа цел откако ќе се достигне одреден број на чинии поставени една врз друга, се започнува со структурирање на нов столб чинии. 

Овој реален пример да се имплементира како структура множество од магацини. Множеството магацини прима
креирање на магацини со одреден капацитет. Откако ќе се надмине капацитетот на последниот
магацин, се креира нов магацин.

Класата треба да содржи метод 

pushElement(int el) со кој во множеството магацини ќе се додава
нова вредност, 

метод pushStackAt(int index, stack s) кој ќе додава нов магацин во множеството,
само ако големината на магацинот соодветствува со дозволениот капацитет, 

popStackAt(int index) кој ќе го одзема магацинот на позиција index во множеството.

Да се напише главна програма во која ќе се покаже функционалноста на методите. */

class StacksSet{
    public int capacity;
    ArrayList<Stack<Integer>> stacks = new ArrayList<Stack<Integer>>();

    public StacksSet(int c){
        this.capacity=c;
    }

    public Stack<Integer> getLastStack(){
        if(stacks.isEmpty()){
            return null;
        }
        return stacks.get(stacks.size()-1);
    }

    public void pushElement(int el){
        Stack<Integer> last = getLastStack();

        if(last!=null && last.size()< capacity){
            last.push(el);
        } else {
            Stack<Integer> nov = new Stack<Integer>();
            nov.push(el);
            stacks.add(nov);
        }
    }

    public void pushStackAt(int index,Stack s){
        if(s.size() == capacity){
            stacks.add(index,s);
        }
    }

    public Stack<Integer> popStackAt(int index){
        if(index>=0 && index < stacks.size()){
            Stack<Integer> stack= stacks.get(index);
            stacks.remove(index);
            return stack;
        }
        return null;
    }
}

public class zad2 {
    public static void main(String[] args) {
    
        StacksSet ss= new StacksSet(3);
        ss.pushElement(4);
        ss.pushElement(5);
        ss.pushElement(1);
        ss.pushElement(7);
        ss.pushElement(8);
        ss.pushElement(9);
        ss.pushElement(2);
        ss.pushElement(6);
 
        System.out.println("After pushing elements:");
        for (int i = 0; i < ss.stacks.size(); i++) {
            for (Integer item : ss.stacks.get(i)) {
                System.out.print(item + " ");
            }
            System.out.println();
        }

        Stack<Integer> s = ss.popStackAt(1);

        System.out.println("\nAfter popping stack at index 1:");
        for (int i = 0; i < ss.stacks.size(); i++) {
            for (Integer item : ss.stacks.get(i)) {
                System.out.print(item + " ");
            }
            System.out.println();
        }

        ss.pushStackAt(1, s);

        System.out.println("\nAfter pushing the stack back at index 1:");
        for (int i = 0; i < ss.stacks.size(); i++) {
            for (Integer item : ss.stacks.get(i)) {
                System.out.print(item + " ");
            }
            System.out.println();
        }


    }
}
