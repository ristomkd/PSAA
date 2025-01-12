package Labs;

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

    // Left -> Root -> Right
    public void inorder(BNode<E> r) {
        if (r != null) {
            inorder(r.left);
            System.out.print(r.info + ", ");
            inorder(r.right);
        }
    }

    // Root -> Left -> Right
    public void preorder(BNode<E> r) {
        if (r != null) {
            System.out.println(r.info + ", ");
            preorder(r.left);
            preorder(r.right);
        }
    }

    // Left -> Right -> Root
    public void postorder(BNode<E> r) {
        if (r != null) {
            postorder(r.left);
            postorder(r.right);
            System.out.println(r.info + ", ");
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
        int count=0;
        if(r.left != null && r.right == null && r.left.right == null){
            count++;
        }
        count+=pairExists(r.left);
        count+=pairExists(r.right);
        return count;
    }
}

public class lab1 {
    public static void main(String[] args) {
        BSTree<Integer> t1 = new BSTree(10);
        t1.insert(8, t1.root);
        t1.insert(6, t1.root);
        t1.insert(2, t1.root);
        t1.insert(15, t1.root);
        t1.insert(12, t1.root);
        t1.insert(11, t1.root);
        t1.insert(20, t1.root);
        t1.insert(16, t1.root);

        int klk = t1.pairExists(t1.root);
        System.out.println(klk);
    }
}
