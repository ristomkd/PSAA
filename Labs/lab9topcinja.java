package Labs;

import java.util.*;

class Topce{
    int index;
    int boja;
    public Topce(){
        index = 0;
        boja = 0;
    }
    public Topce(int index, int boja){
        this.index = index;
        this.boja = boja;
    }
}

public class lab9topcinja {
    public static void main(String[] args) throws Exception {
        ArrayList<Topce> topcinja = new ArrayList<Topce>();
        Topce t1 = new Topce(0,1);
        Topce t2 = new Topce(1,1);
        Topce t3 = new Topce(2,0);
        Topce t4 = new Topce(3,1);
        Topce t5 = new Topce(4,0);
        Topce t6 = new Topce(5,0);
        Topce t7 = new Topce(6,0);
        Topce t8 = new Topce(7,1);

        topcinja.add(t1);
        topcinja.add(t2);
        topcinja.add(t3);
        topcinja.add(t4);
        topcinja.add(t5);
        topcinja.add(t6);
        topcinja.add(t7);
        topcinja.add(t8);

        int getwirelength = wirelength(topcinja);

        System.out.println(getwirelength);

    }

    private static int wirelength(ArrayList<Topce> topcinja) {
        Stack<Topce> stack = new Stack<Topce>();
        int dolzina = 0;
        for(int i=0;i<topcinja.size();i++){
            Topce pom = topcinja.get(i);
            if(stack.isEmpty()){
                stack.push(pom);
            }else{
                if(pom.boja!=stack.peek().boja){
                    dolzina += pom.index - stack.peek().index;
                    stack.pop();
                }else{
                    stack.push(pom);
                }
            }
        }

        return dolzina;
    }
}