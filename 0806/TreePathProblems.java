import java.util.*;

public class TreePathProblems {

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

    public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, path, res);
        return res;
    }

    private static void dfs(TreeNode node, List<Integer> path, List<List<Integer>> res) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null) {
            res.add(new ArrayList<>(path));
        } else {
            dfs(node.left, path, res);
            dfs(node.right, path, res);
        }
        path.remove(path.size() - 1);
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        if (root.left == null && root.right == null) return root.val;
        return root.val + Math.max(maxRootToLeafSum(root.left), maxRootToLeafSum(root.right));
    }

    static int maxDiameterSum = Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root) {
        maxDiameterSum = Integer.MIN_VALUE;
        helper(root);
        return maxDiameterSum;
    }

    private static int helper(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, helper(node.left));
        int right = Math.max(0, helper(node.right));
        maxDiameterSum = Math.max(maxDiameterSum, node.val + left + right);
        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TreeNode root = buildTreeFromInput(sc);

        List<List<Integer>> paths = allRootToLeafPaths(root);
        System.out.println("所有根到葉節點路徑:");
        for (List<Integer> path : paths) {
            System.out.println(path);
        }

        System.out.print("請輸入目標路徑和: ");
        int target = sc.nextInt();
        System.out.println("是否存在路徑和為 " + target + ": " + (hasPathSum(root, target) ? "是" : "否"));

        int maxSum = maxRootToLeafSum(root);
        System.out.println("最大根到葉路徑和: " + maxSum);

        int diameterSum = maxPathSum(root);
        System.out.println("任意兩節點間最大路徑和(樹的直徑): " + diameterSum);

        sc.close();
    }
}
