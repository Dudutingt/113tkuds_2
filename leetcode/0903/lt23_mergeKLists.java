import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            current.next = node;
            current = current.next;

            if (node.next != null) {
                pq.offer(node.next);
            }
        }

        return dummy.next;
    }
}

/*
解題思路：
1. 題目要合併 k 個已排序的鏈表，最後輸出一個有序的鏈表。
2. 常見解法有兩種：
   - 分治合併：兩兩合併鏈表，類似 merge sort，時間複雜度 O(N log k)。
   - 最小堆（優先隊列）：一次性將所有鏈表的頭節點放進最小堆，每次取出當前最小值，並推入下一個節點，直到處理完畢。
3. 本程式採用最小堆方法：
   - 初始化：將所有非空鏈表的頭節點放入最小堆。
   - 每次從堆中取出最小節點，接到結果鏈表後面。
   - 若該節點有後續節點，將其後續節點放回堆中。
   - 重複直到堆空。
4. 時間複雜度：
   - 每個節點都會被插入並取出堆一次，操作代價為 O(log k)。
   - 總共 N 個節點，因此整體時間複雜度為 O(N log k)。
5. 空間複雜度：
   - 主要來自最小堆，最多同時存放 k 個節點，因此為 O(k)。
*/
