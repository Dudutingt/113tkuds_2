import java.util.*;

public class MultiWayTreeAndDecisionTree {

    static class MultiTreeNode {
        int val;
        List<MultiTreeNode> children = new ArrayList<>();
        MultiTreeNode(int v) { val = v; }
    }

    public static MultiTreeNode buildMultiTree(Scanner sc) {
        System.out.print("請輸入節點數量: ");
        int n = sc.nextInt();
        if (n == 0) return null;

        Map<Integer, MultiTreeNode> nodes = new HashMap<>();
        System.out.println("請輸入每個節點的值:");
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            nodes.put(i, new MultiTreeNode(val));
        }

        System.out.println("請依序輸入每個節點的子節點數量與子節點索引:");
        for (int i = 0; i < n; i++) {
            MultiTreeNode node = nodes.get(i);
            int childCount = sc.nextInt();
            for (int j = 0; j < childCount; j++) {
                int childIdx = sc.nextInt();
                node.children.add(nodes.get(childIdx));
            }
        }
        return nodes.get(0);
    }

    public static void dfs(MultiTreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        for (MultiTreeNode child : root.children) {
            dfs(child);
        }
    }

    public static void bfs(MultiTreeNode root) {
        if (root == null) return;
        Queue<MultiTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            MultiTreeNode node = queue.poll();
            System.out.print(node.val + " ");
            for (MultiTreeNode child : node.children) {
                queue.offer(child);
            }
        }
    }

    // 模擬簡單決策樹（猜數字遊戲）
    public static void decisionTreeGame(Scanner sc) {
        System.out.println("請想一個1~100的數字，我會猜，請輸入指令：'higher', 'lower', 'correct'");
        int low = 1, high = 100;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            System.out.println("我猜：" + mid);
            String response = sc.next();
            if (response.equals("correct")) {
                System.out.println("猜對了！");
                return;
            } else if (response.equals("higher")) {
                low = mid + 1;
            } else if (response.equals("lower")) {
                high = mid - 1;
            } else {
                System.out.println("請輸入 'higher', 'lower' 或 'correct'");
            }
        }
        System.out.println("遊戲結束，未猜中！");
    }

    public static int height(MultiTreeNode root) {
        if (root == null) return 0;
        int h = 0;
        for (MultiTreeNode child : root.children) {
            h = Math.max(h, height(child));
        }
        return h + 1;
    }

    
    public static void printDegree(MultiTreeNode root) {
        if (root == null) return;
        System.out.println("節點 " + root.val + " 度數: " + root.children.size());
        for (MultiTreeNode child : root.children) {
            printDegree(child);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MultiTreeNode root = buildMultiTree(sc);

        System.out.print("多路樹深度優先走訪: ");
        dfs(root);
        System.out.println();

        System.out.print("多路樹廣度優先走訪: ");
        bfs(root);
        System.out.println();

        System.out.println("節點度數:");
        printDegree(root);

        decisionTreeGame(sc);

        System.out.println("多路樹高度: " + height(root));

        sc.close();
    }
}
