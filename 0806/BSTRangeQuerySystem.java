import java.util.*;

public class BSTRangeQuerySystem {

    static class TreeNode {
        int val;
        TreeNode left, right;
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
        System.out.println("請輸入節點值(數字):");
        for (int i = 0; i < n; i++) {
            int v = sc.nextInt();
            root = insert(root, v);
        }
        return root;
    }

    public static List<Integer> rangeQuery(TreeNode root, int min, int max) {
        List<Integer> res = new ArrayList<>();
        rangeQueryHelper(root, min, max, res);
        return res;
    }

    private static void rangeQueryHelper(TreeNode root, int min, int max, List<Integer> res) {
        if (root == null) return;
        if (root.val > min) rangeQueryHelper(root.left, min, max, res);
        if (root.val >= min && root.val <= max) res.add(root.val);
        if (root.val < max) rangeQueryHelper(root.right, min, max, res);
    }

    public static int rangeCount(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min) return rangeCount(root.right, min, max);
        else if (root.val > max) return rangeCount(root.left, min, max);
        else return 1 + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
    }

    public static int rangeSum(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min) return rangeSum(root.right, min, max);
        else if (root.val > max) return rangeSum(root.left, min, max);
        else return root.val + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
    }

    
    public static int closestValue(TreeNode root, int target) {
        int closest = root.val;
        TreeNode cur = root;
        while (cur != null) {
            if (Math.abs(cur.val - target) < Math.abs(closest - target)) {
                closest = cur.val;
            }
            if (target < cur.val) cur = cur.left;
            else if (target > cur.val) cur = cur.right;
            else break;
        }
        return closest;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TreeNode root = buildBST(sc);

        System.out.print("請輸入查詢範圍下限 min: ");
        int min = sc.nextInt();
        System.out.print("請輸入查詢範圍上限 max: ");
        int max = sc.nextInt();

        List<Integer> rangeList = rangeQuery(root, min, max);
        System.out.println("範圍內節點值: " + rangeList);

        int count = rangeCount(root, min, max);
        System.out.println("範圍內節點數量: " + count);

        int sum = rangeSum(root, min, max);
        System.out.println("範圍內節點值總和: " + sum);

        System.out.print("請輸入要查找最接近的目標值: ");
        int target = sc.nextInt();
        int closest = closestValue(root, target);
        System.out.println("最接近 " + target + " 的節點值為: " + closest);

        sc.close();
    }
}
