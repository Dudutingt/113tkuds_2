import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {
        int val; TreeNode left, right;
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

    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    private static boolean isValidBSTHelper(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;
        if (min != null && node.val <= min) return false;
        if (max != null && node.val >= max) return false;
        return isValidBSTHelper(node.left, min, node.val)
            && isValidBSTHelper(node.right, node.val, max);
    }

    
    static TreeNode first = null, second = null, prev = null;
    public static void findWrongNodes(TreeNode root) {
        first = second = prev = null;
        inorderFind(root);
    }

    private static void inorderFind(TreeNode root) {
        if (root == null) return;
        inorderFind(root.left);
        if (prev != null && root.val < prev.val) {
            if (first == null) first = prev;
            second = root;
        }
        prev = root;
        inorderFind(root.right);
    }

    public static void recoverBST(TreeNode root) {
        findWrongNodes(root);
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    
    static class ResultType {
        boolean isBST;
        int size;
        int minVal, maxVal;
        ResultType(boolean b, int s, int min, int max) {
            isBST = b; size = s; minVal = min; maxVal = max;
        }
    }

    public static int minRemoveForBST(TreeNode root) {
        int totalNodes = countNodes(root);
        int maxBSTSize = maxBSTSubtree(root).size;
        return totalNodes - maxBSTSize;
    }

    private static int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private static ResultType maxBSTSubtree(TreeNode root) {
        if (root == null) return new ResultType(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        ResultType left = maxBSTSubtree(root.left);
        ResultType right = maxBSTSubtree(root.right);

        if (left.isBST && right.isBST && root.val > left.maxVal && root.val < right.minVal) {
            return new ResultType(true, left.size + right.size + 1,
                Math.min(root.val, left.minVal),
                Math.max(root.val, right.maxVal));
        } else {
            return new ResultType(false, Math.max(left.size, right.size), 0, 0);
        }
    }

    public static void inorderPrint(TreeNode root) {
        if (root == null) return;
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TreeNode root = buildTreeFromInput(sc);

        System.out.println("是否為有效BST: " + (isValidBST(root) ? "是" : "否"));

        findWrongNodes(root);
        if (first != null && second != null) {
            System.out.println("不符合BST的節點值: " + first.val + " 與 " + second.val);
        } else {
            System.out.println("未發現不符合BST的節點");
        }

        System.out.println("修復後BST中序列印:");
        recoverBST(root);
        inorderPrint(root);
        System.out.println();

        int removeCount = minRemoveForBST(root);
        System.out.println("最少需移除節點數讓樹成BST: " + removeCount);

        sc.close();
    }
}
