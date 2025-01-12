package Labs2;
import java.util.*;

class Map<K extends Comparable<K>, E> {
    public K key;
    public E value;

    public Map(K key, E value) {
        this.key = key;
        this.value = value;
    }
}

class SLLNode<E> {
    public E info;
    public SLLNode<E> next;

    public SLLNode(E info, SLLNode<E> next) {
        this.info = info;
        this.next = next;
    }
}

class SLLHT<K extends Comparable<K>, E> {
    private SLLNode<Map<K, E>>[] htable;

    public SLLHT(int n) {
        htable = new SLLNode[n];
        for (int i = 0; i < n; i++) {
            htable[i] = null;
        }
    }

    private int hash(K key) {
        return (Integer) key % htable.length;
    }

    public int getSize() {
        return htable.length;
    }

    public SLLNode<Map<K, E>> getHtable(int i) {
        return htable[i];
    }

    public SLLNode<Map<K, E>> find(K look) {
        int h = hash(look); 

        for (SLLNode<Map<K, E>> node = htable[h]; node != null; node = node.next) {
            if (look.equals(node.info.key)) {
                return node;
            }
        }

        return null;
    }

    public void insert(K key, E value) {
        Map<K, E> entry = new Map(key, value);

        int h = hash(key);

        for (SLLNode<Map<K, E>> node = htable[h]; node != null; node = node.next) {
            if (key.equals(node.info.key)) {
                node.info = entry;
                return;
            }
        }

        htable[h] = new SLLNode<Map<K, E>>(entry, htable[h]);
    }

    public void delete(K key) {
        int h = hash(key);

        for (SLLNode<Map<K, E>> pred = null, node = htable[h]; node != null; pred = node, node = node.next) {
            if (key.equals(node.info.key)) {
                if (pred == null) {
                    htable[h] = node.next;
                } else {
                    pred.next = node.next;
                }
                return;
            }
        }
    }
}

class Vraboten {
    public String ime, prezime;
    public int id;
    public String pozicija;
    public int iskustvo; //vo godini

    public Vraboten(String i, String p, int id, String p2, int isk){
        this.ime=i;
        this.prezime=p;
        this.id=id;
        this.pozicija=p2;
        this.iskustvo=isk;
    }

    @Override
    public String toString(){
        return "Vraboteniot" + this.ime + " " + this.prezime + " so identifikacija" + id + " na rabotna pozicjia" + this.pozicija 
                + " so iskustvo od " + this.iskustvo + "godini";
    }
}

class Zadaca{
    public String sodrzina;
    public Vraboten vraboten;
    public boolean status; //1-aktivna 0-zavrshena
    public String datetime;

    public Zadaca(String s, Vraboten v, boolean status, String datetime){
        this.sodrzina=s;
        this.vraboten=v;
        this.status=status;
        this.datetime=datetime;
    }

    @Override
    public String toString() {
        return String.format("Задача: %s (Краен рок: %s)", this.sodrzina, this.datetime);
    }
}
/*
 * Една компанија има потреба од систем за распределба на задачи по вработени.
 * За секој вработен се знае името, презимето, број за идентификација, работна
 * позиција и искуство. За секоја задача се знае содржината на задачата, на кој
 * вработен е доделена истата, статус (активна/завршена) и крајниот рок до кој
 * задачата треба да биде завршена. Со помош на хеш табели да се реализира
 * чување на информациите за вработените и задачите.
 * 
 * Потоа, да се внесе информација за два вработени и за нив да се прикажат
 * активните задачи кои им се доделени. Доколку е внесена невалидна информација
 * за вработен, да се прикаже порака за грешка. Доколку вработениот нема активни
 * задачи да се испечати порака која го известува корисникот за тоа.
 */

public class hash{
    public static void main(String[] args) {
        
        Vraboten[] vraboteni = new Vraboten[2];
            vraboteni[0]= new Vraboten("Petar", "Petrovski", 1, "Programer", 5);
            vraboteni[1]= new Vraboten("Ana", "Anovska", 2, "Dizajner", 3);


        Zadaca[] zadaci = new Zadaca[4];
        zadaci[0] = new Zadaca("Software Testing",vraboteni[0], true, "22/12/2023");
        zadaci[1]= new Zadaca("Dizajn na Korisnicko Iskustvo", vraboteni[1], true, "24/12/2023");
        zadaci[2]= new Zadaca("Software Development", vraboteni[0], true, "24/12/2023");
        zadaci[3]= new Zadaca("Bla Bla", vraboteni[0], false, "21/12/2023");

        SLLHT<Integer, Vraboten> vrabotenHash = new SLLHT<Integer, Vraboten>(4);
        vrabotenHash.insert(vraboteni[0].id, vraboteni[0]);
        vrabotenHash.insert(vraboteni[1].id, vraboteni[1]);

        SLLHT<Integer, Zadaca> zadaciHashed = new SLLHT<Integer,Zadaca>(4);
        zadaciHashed.insert(hashZadaca(zadaci[0].vraboten.id, zadaci[0].sodrzina),zadaci[0]);
        zadaciHashed.insert(hashZadaca(zadaci[1].vraboten.id, zadaci[1].sodrzina),zadaci[1]);
        zadaciHashed.insert(hashZadaca(zadaci[2].vraboten.id, zadaci[2].sodrzina),zadaci[2]);
        zadaciHashed.insert(hashZadaca(zadaci[3].vraboten.id, zadaci[3].sodrzina),zadaci[3]);

        printAllActiveTasks(vrabotenHash, zadaciHashed);
    }

    private static Integer hashZadaca(int id, String sodrzina) {
    int hash= 0;
    for(char c: sodrzina.toCharArray()){
        hash += (int) c;
    }

    return hash + id * 1000;
    }

    private static LinkedList<Zadaca> getActiveTasksForVraboten(Vraboten vraboten, SLLHT<Integer, Zadaca> zadaciHashed) {
        LinkedList<Zadaca> activeTasks = new LinkedList<>();

        // Пребарај низ сите кофички на хеш табелата
        for (int i = 0; i < zadaciHashed.getSize(); i++) {
            SLLNode<Map<Integer, Zadaca>> node = zadaciHashed.getHtable(i);
            while (node != null) {
                Zadaca zadaca = node.info.value;
                if (zadaca.vraboten.id == vraboten.id && zadaca.status) { // Проверка за активна задача
                    activeTasks.add(zadaca);
                }
                node = node.next;
            }
        }

        return activeTasks;
    }

    private static void printAllActiveTasks(SLLHT<Integer, Vraboten> vrabotenHash,
            SLLHT<Integer, Zadaca> zadaciHashed) {
        // Пројди низ сите кофички на хеш табелата за вработени
        for (int i = 0; i < vrabotenHash.getSize(); i++) {
            SLLNode<Map<Integer, Vraboten>> node = vrabotenHash.getHtable(i);
            while (node != null) {
                Vraboten vraboten = node.info.value;
                System.out.println("Вработениот: " + vraboten);

                LinkedList<Zadaca> activeTasks = getActiveTasksForVraboten(vraboten, zadaciHashed);
                if (activeTasks.isEmpty()) {
                    System.out.println("Нема активни задачи.");
                } else {
                    System.out.println("Активни задачи:");
                    for (Zadaca zadaca : activeTasks) {
                        System.out.println(" - " + zadaca);
                    }
                }

                node = node.next;
            }
        }
    }
}