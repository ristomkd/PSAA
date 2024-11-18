package IspitniZad;
import java.util.ArrayList;
import java.util.List;
// Дадена е еднострано поврзана листа од стрингови, така што секој јазел содржи по еден карактер. 
// Всушност, кога ќе се погледне цела листа, се забележуваат зборови, одделени со запирка. 
// Треба да се трансформира листата така што секој збор ќе стане посебен јазел, и дополнително ќе се избришат запирките.

// Забелешка 1: После буквите од последниот збор нема запирка.
// Забелешка 2: Не треба да се менува main функцијата, туку само во void putWordsTogether(SLL<String> list).

// Излез: Прво се печати оригиналната листа со стрингови од по еден карактер, па трансформираната листа од споени зборови, без запирките.

// Пример:


// Влез:
// c -> a -> t -> , -> d -> o -> g -> , -> c -> o -> w

// Излез:
// cat -> dog -> cow

class Node<E> {
    protected E data;
    protected Node<E> next;
    
    public Node() {
        data = null;
        next = null;
    }
    
    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }
}

class SLinkedList<E> {
    private Node<E> head;
    
    public Node<E> getHead() {
        return head;
    }
    
    public SLinkedList() {
        head = null;
    }
    
    public void insertFirst(E data) {
        Node<E> first = new Node<>(data, head);
        head = first;
    }
    
    public void insertAfter(E e, Node<E> n) {
        if (n != null) {
            Node<E> node = new Node<>(e, n.next);
            n.next = node;
        } else {
            System.out.println("Error.");
        }
    }
    
    public void insertBefore(E e, Node<E> n) {
        if (head != null) {
            Node<E> tmp = head;
            if (tmp == n) {
                this.insertFirst(e);
                return;
            }
            
            while (tmp.next != n && tmp.next != null) {
                tmp = tmp.next;
            }
            
            if (tmp.next == n) {
                Node<E> node = new Node<>(e, n);
                tmp.next = node;
            }
        }
    }
    
    public void insertLast(E e) {
        if (head != null) {
            Node<E> tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            
            Node<E> last = new Node<>(e, null);
            tmp.next = last;
        } else {
            this.insertFirst(e);
        }
    }
    
    public void deleteFirst() {
        if (head != null) {
            head = head.next;
        } else {
            System.out.println("Error.");
        }
    }
    
    public void printList() {
        Node<E> tmp = head;
        
        while (tmp.next != null) {
            System.out.print(tmp.data + " -> ");
            tmp = tmp.next;
        }
        System.out.println(tmp.data);
    }
    
    public void reverse(){
       if(head!=null){
        Node<E> curr=head;
        Node<E> prev=null;
        Node<E> next=null;

        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        head=prev;
    }
    }
}
public class zad3 {
    public static void main(String[] args) {
        SLinkedList<String> list= new SLinkedList<>();

        list.insertLast("c");
        list.insertLast("a");
        list.insertLast("t");
        list.insertLast(",");
        list.insertLast("d");
        list.insertLast("o");
        list.insertLast("g");
        list.insertLast(",");
        list.insertLast("c");
        list.insertLast("o");
        list.insertLast("w");
        list.printList();
       
        transformiraj(list);
    }
        
    private static void transformiraj(SLinkedList<String> list) {
        ArrayList<String> lista = new ArrayList<String>();
        Node<String> tmp = list.getHead();
        String zbor="";
        SLinkedList<String> nov=new SLinkedList<>();
        
        // PRV NACIN
        while(tmp!=null){
            while(tmp!=null && !tmp.data.equals(",")){
                zbor+=tmp.data;
                tmp=tmp.next;
            }
            if(!zbor.isEmpty()){
                nov.insertLast(zbor);
                zbor="";
            }
            if( tmp!=null && tmp.data.equals(",")){
                nov.insertLast(tmp.data);
                tmp=tmp.next;
            }
        }
        
        // VTOR NACIN
        while(tmp!=null){
            lista.add(tmp.data);
            tmp=tmp.next;
        }
        //System.out.print(lista);

        tmp=list.getHead();
        for(String e : lista){
            if(e.equals(",")){
                nov.insertLast(zbor);
                zbor="";
            }
            else {
                zbor+=e;
            }
        }
        nov.insertLast(zbor); 
        nov.printList();    
}
}
