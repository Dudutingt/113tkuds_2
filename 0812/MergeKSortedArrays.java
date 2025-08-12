import java.util.*;

public class MergeKSortedArrays {
    static class Node {
        int val;
        int arrIdx;
        int elemIdx;
        Node(int v, int a, int e) { val=v; arrIdx=a; elemIdx=e; }
    }

    public static List<Integer> merge(List<List<Integer>> lists) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.val - b.val);
        for (int i = 0; i < lists.size(); i++) {
            if (!lists.get(i).isEmpty()) {
                pq.offer(new Node(lists.get(i).get(0), i, 0));
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            Node n = pq.poll();
            res.add(n.val);
            int nextIdx = n.elemIdx + 1;
            if (nextIdx < lists.get(n.arrIdx).size()) {
                pq.offer(new Node(lists.get(n.arrIdx).get(nextIdx), n.arrIdx, nextIdx));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> in1 = Arrays.asList(
            Arrays.asList(1,4,5),
            Arrays.asList(1,3,4),
            Arrays.asList(2,6)
        );
        System.out.println(merge(in1)); 

        List<List<Integer>> in2 = Arrays.asList(
            Arrays.asList(1,2,3),
            Arrays.asList(4,5,6),
            Arrays.asList(7,8,9)
        );
        System.out.println(merge(in2)); 

        List<List<Integer>> in3 = Arrays.asList(
            Arrays.asList(1),
            Arrays.asList(0)
        );
        System.out.println(merge(in3)); 
    }
}
