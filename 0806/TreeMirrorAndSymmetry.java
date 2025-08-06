import java.util.*;

public class TreeMirrorAndSymmetry {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static TreeNode buildTreeFromInput(Scanner sc) {
        System.out.print("請輸入節點數量(含空節點 -1): ");
        int n = sc.nextInt();
        if (n == 0) return null;
        List<TreeNode> nodes = new ArrayList<>();
        System.out.println("請依層序輸入節點值，空節點請輸入 -1:");
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            nodes.add(val == -1 ? null : new TreeNode(val));
        }
        int childIndex = 1;
        for (int i = 0; i < n && childIndex < n; i++) {
            TreeNode curr = nodes.get(i);
            if (curr != null) {
                if (childIndex < n) curr.left = nodes.get(childIndex++);
                if (childIndex < n) curr.right = nodes.get(childIndex++);
            }
        }
        return nodes.get(0);
    }

    public static boolean isSymmetric(TreeNode root) {
        return root == null || isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
            && isMirror(t1.left, t2.right)
            && isMirror(t1.right, t2.left);
    }

    public static void mirror(TreeNode root) {
        if (root == null) return;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        mirror(root.left);
        mirror(root.right);
    }

    public static boolean areMirrors(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
            && areMirrors(t1.left, t2.right)
            && areMirrors(t1.right, t2.left);
    }

    
    public static boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return t == null;
        if (isSameTree(s, t)) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private static boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.val != t.val) return false;
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

  
    public static void inorderPrint(TreeNode root) {
        if (root == null) return;
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("請輸入第一棵樹:");
        TreeNode tree1 = buildTreeFromInput(sc);

        System.out.println("請輸入第二棵樹:");
        TreeNode tree2 = buildTreeFromInput(sc);

        System.out.println("第一棵樹是否對稱: " + (isSymmetric(tree1) ? "是" : "否"));

        System.out.print("將第一棵樹轉成鏡像後中序列印: ");
        mirror(tree1);
        inorderPrint(tree1);
        System.out.println();

        System.out.println("兩棵樹是否互為鏡像: " + (areMirrors(tree1, tree2) ? "是" : "否"));

        System.out.println("第二棵樹是否為第一棵樹的子樹: " + (isSubtree(tree1, tree2) ? "是" : "否"));

        sc.close();
    }
}
