/* Да се напише функција која како аргумент го добива head јазолот на единечно поврзана листа и вредност N (цел број). 
Функцијата треба да ја измине листата и да го отстрани N-тиот јазол од крајот на листата. 
Задачата да се реши и со класата SLinkedList и со класата LinkedList. */
package Labs;

import java.util.LinkedList;

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
    protected int count;

    public Node<E> getHead() {
        return head;
    }

    public SLinkedList() {
        head = null;
    }

    public void insertFirst(E e) {
        Node<E> first = new Node(e, head);
        head = first;
        count++;
    }

    public void insertAfter(E e, Node<E> n) {
        if (n != null) {
            Node<E> node = new Node(e, n.next);
            n.next = node;
            count++;
        } else {
            System.out.println("Error.");
        }
    }

    public void insertBefore(E e, Node<E> n) {
        if (head != null) {
            Node<E> tmp = head;
            if (tmp == n) {
                this.insertFirst(e);
                count++;
                return;
            }

            while (tmp.next != n && tmp.next != null) {
                tmp = tmp.next;
            }

            if (tmp.next == n) {
                Node<E> node = new Node(e, n);
                tmp.next = node;
                count++;
            }
        }
    }

    public void insertLast(E e) {
        if (head != null) {
            Node<E> tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }

            Node<E> last = new Node(e, null);
            tmp.next = last;
            count++;
        } else {
            this.insertFirst(e);
        }
    }

    public void deleteFirst() {
        if (head != null) {
            head = head.next;
            count--;
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

    public void izbrishi(Node<E> head, int N) {
        if (N > count) {
            return;
        }
        if (count == N) {
            deleteFirst();
            return;
        }
        Node<E> tmp = head;
        Node<E> prev = null;
        int i = 0;
        while (i <= count - N - 2) {
            tmp = tmp.next;
            i++;
        }
        prev = tmp;
        tmp = tmp.next;
        prev.next = tmp.next;
        count--;
        printList();
    }

}

public class Lab4 {
    public static void main(String[] args) {
        SLinkedList list = new SLinkedList();
        LinkedList listF = new LinkedList();
        list.insertLast(1);
        list.insertLast(2);
        list.insertLast(3);
        list.insertLast(4);
        list.insertLast(5);
        list.insertLast(6);
        list.insertLast(7);
        Node headd = new Node();
        headd = list.getHead();
        list.izbrishi(headd, 5);

        listF.add(1);
        listF.add(2);
        listF.add(3);
        listF.add(4);
        listF.add(5);
        listF.add(6);
        listF.add(7);
        izmeni(listF, 5);
    }

    private static void izmeni(LinkedList<Integer> list, int i) {
        if (list.isEmpty() || i <= 0 || i > list.size()) {
            return; // nevaliden vlez;
        }
        int pos = list.size() - i;
        list.remove(pos);
        System.out.println(list);

    }
}