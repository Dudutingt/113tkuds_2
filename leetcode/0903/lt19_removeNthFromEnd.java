class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        slow.next = slow.next.next;
        return dummy.next;
    }
}

/*
解題思路：
這題用快慢指針解法。
1. 建立一個 dummy 節點，指向 head，方便處理刪除頭節點的情況。
2. 先讓 fast 指針前進 n+1 步，與 slow 保持 n 的距離。
3. 然後同時移動 fast 與 slow，直到 fast 到達尾端。
4. 此時 slow 停在要刪除節點的前一個位置，直接調整 slow.next 指向 slow.next.next。
時間複雜度 O(sz)，空間複雜度 O(1)。
*/
