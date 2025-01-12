import java.util.LinkedList;
import java.util.Queue;

class BNode<E extends Comparable<E>> {
    public E info;
    public BNode<E> left, right;

    public BNode(E info) {
        this.info = info;
        this.left = null;
        this.right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }
}

class BSTree<E extends Comparable<E>> {
    public BNode<E> root;

    public BSTree() {
        root = null;
    }

    public BSTree(E info) {
        root = new BNode(info);
    }

    public void inorder(BNode<E> r) {
        if (r != null) {
            inorder(r.left);
            System.out.print(r.info + ", ");
            inorder(r.right);
        }
    }

    public BNode<E> insert(E info, BNode<E> r) {
        if (r == null) {
            return new BNode(info);
        }

        int comp = info.compareTo(r.info);
        if (comp < 0) { // left
            r.left = insert(info, r.left);
        } else if (comp > 0) { // right
            r.right = insert(info, r.right);
        } else {
            // ne pravi nishto
        }

        return r;
    }

    public boolean contains(E info, BNode<E> r) {
        if (r == null) {
            return false;
        }

        int comp = info.compareTo(r.info);
        if (comp < 0) {
            return contains(info, r.left);
        } else if (comp > 0) {
            return contains(info, r.right);
        } else {
            return true;
        }
    }

    public BNode<E> remove(E info, BNode<E> r) {
        if (r == null) {
            return r;
        }

        int comp = info.compareTo(r.info);
        if (comp < 0) {
            r.left = remove(info, r.left);
        } else if (comp > 0) {
            r.right = remove(info, r.right);
        } else { // brishi go jazolot info
            if (r.left != null && r.right != null) {
                r.info = findMin(r.right).info;
                r.right = remove(r.info, r.right);
            } else {
                if (r.left != null) {
                    return r.left;
                } else if (r.right != null) {
                    return r.right;
                } else {
                    return null;
                }
            }
        }

        return r;
    }

    private BNode<E> findMin(BNode<E> r) {
        if (r == null) {
            return null;
        } else if (r.left == null) {
            return r;
        } else {
            return findMin(r.left);
        }
    }

    public Integer pairExists(BNode<E> r) {
        if (r == null)
            return 0;

        int imaLevo = pairExists(r.left);
        int imaDesno = pairExists(r.right);

        if (imaLevo == 1 || imaDesno == 1)
            return 1;
        else if (r.left != null && r.right == null && r.left.right == null) {
            return 1;
        }
        return 0;
    }

    public BNode<E> findRight(BNode<E> r, E value, int level) {
        if (r == null) {
            return null;
        }
        Queue<BNode<E>> q = new LinkedList<>();
        q.add(r);
        int lvl = 0;
        while (true) {
            Queue<BNode<E>> tmp = new LinkedList<>();
            while (!q.isEmpty()) {
                tmp.add(q.poll());
            }
            if (lvl == level) {
                while (!tmp.isEmpty()) {
                    BNode<E> curr = tmp.poll();
                    if (curr.info.equals(value))
                        return tmp.poll();
                }
                return null;
            }
            while (!tmp.isEmpty()) {
                BNode<E> node = tmp.poll();
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }
            lvl++;
        }
    }
}

public class lab3 {
    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree<>(10);
        tree.insert(6, tree.root);
        tree.insert(5, tree.root);
        tree.insert(3, tree.root);
        tree.insert(7, tree.root);
        tree.insert(9, tree.root);
        tree.insert(12, tree.root);
        tree.insert(11, tree.root);
        System.out.println(tree.findRight(tree.root, 3, 3).info);
    }
}