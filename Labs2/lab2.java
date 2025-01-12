package Labs;

class BNode<E> {
    public E info;
    public BNode<E> left, right;
    static int LEFT = 1, RIGHT = 2;

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

class BTree<E> {
    public BNode<E> root;

    public BTree() {
        root = null;
    }

    public BTree(E info) {
        root = new BNode<E>(info);
    }

    public BNode<E> addChild(BNode<E> node, int where, E info) {
        BNode<E> tmp = new BNode<E>(info);

        if (where == BNode.LEFT) {
            if (node.left != null) {
                return null;
            }
            node.left = tmp;
        } else {
            if (node.right != null) {
                return null;
            }
            node.right = tmp;
        }

        return tmp;
    }
}

public class lab2 {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<Integer>(-1);
        tree.addChild(tree.root, 1, -1);
        tree.addChild(tree.root, 2, -1);
        tree.addChild(tree.root, 1, -1);
        tree.addChild(tree.root, 2, -1);
        tree.addChild(tree.root, 2, -1);
        recover(tree);

    }

    public static void recover(BTree<Integer> tree) {
        recoverRecursive(tree, tree.root);
    }

    public static void recoverRecursive(BTree<Integer> tree, BNode<Integer> r) {
        if (r == null)
            return;
        if (tree.root == r) {
            r.info = 0;
        }
        if (r.left != null)
            r.left.info = r.info * 2 + 1;
        if (r.right != null)
            r.right.info = r.info * 2 + 2;

        recoverRecursive(tree, r.left);
        recoverRecursive(tree, r.right);
    }
}
