import java.util.*;

public class BSTKthElement {

    static class TreeNode {
        int val; TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }

    public static TreeNode buildBST(Scanner sc) {
        System.out.print("請輸入BST節點數量: ");
        int n = sc.nextInt();
        TreeNode root = null;
        System.out.println("請輸入節點值:");
        for (int i = 0; i < n; i++) {
            root = insert(root, sc.nextInt());
        }
        return root;
    }

    public static void inorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    public static int kthSmallest(TreeNode root, int k) {
        List<Integer> arr = new ArrayList<>();
        inorder(root, arr);
        if (k <= 0 || k > arr.size()) return -1;
        return arr.get(k - 1);
    }

    public static int kthLargest(TreeNode root, int k) {
        List<Integer> arr = new ArrayList<>();
        inorder(root, arr);
        if (k <= 0 || k > arr.size()) return -1;
        return arr.get(arr.size() - k);
    }

    public static List<Integer> kthSmallestRange(TreeNode root, int k, int j) {
        List<Integer> arr = new ArrayList<>();
        inorder(root, arr);
        if (k > j || k <= 0 || j > arr.size()) return Collections.emptyList();
        return arr.subList(k - 1, j);
    }

    
    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) root.left = deleteNode(root.left, key);
        else if (key > root.val) root.right = deleteNode(root.right, key);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            else {
                TreeNode minNode = findMin(root.right);
                root.val = minNode.val;
                root.right = deleteNode(root.right, minNode.val);
            }
        }
        return root;
    }

    private static TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeNode root = buildBST(sc);

        System.out.print("請輸入 k 以找第 k 小元素: ");
        int k = sc.nextInt();
        int kthSmall = kthSmallest(root, k);
        System.out.println("第 " + k + " 小元素為: " + (kthSmall == -1 ? "不存在" : kthSmall));

        System.out.print("請輸入 k 以找第 k 大元素: ");
        int k2 = sc.nextInt();
        int kthLarge = kthLargest(root, k2);
        System.out.println("第 " + k2 + " 大元素為: " + (kthLarge == -1 ? "不存在" : kthLarge));

        System.out.print("請輸入 k j 以找第 k 到第 j 小元素區間（用空白分隔）: ");
        int k3 = sc.nextInt();
        int j = sc.nextInt();
        List<Integer> rangeList = kthSmallestRange(root, k3, j);
        System.out.println("第 " + k3 + " 到第 " + j + " 小元素區間: " + rangeList);

        System.out.print("請輸入要刪除的節點值: ");
        int del = sc.nextInt();
        root = deleteNode(root, del);
        List<Integer> afterDel = new ArrayList<>();
        inorder(root, afterDel);
        System.out.println("刪除節點後中序列印: " + afterDel);

        sc.close();
    }
}

