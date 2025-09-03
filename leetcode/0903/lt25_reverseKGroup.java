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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;
        ListNode curr = head;

        while (true) {
            ListNode check = curr;
            for (int i = 0; i < k; i++) {
                if (check == null) return dummy.next;
                check = check.next;
            }

            ListNode prev = null;
            ListNode tempCurr = curr;
            for (int i = 0; i < k; i++) {
                ListNode next = tempCurr.next;
                tempCurr.next = prev;
                prev = tempCurr;
                tempCurr = next;
            }

            ListNode nextGroupStart = tempCurr;
            ListNode groupStart = curr;
            prevGroupEnd.next = prev;
            groupStart.next = nextGroupStart;

            prevGroupEnd = groupStart;
            curr = nextGroupStart;
        }
    }
}

/*
解題思路：
1. 題目要求每 k 個節點反轉一次，如果剩餘節點不足 k 個則保持原順序。
2. 使用虛擬頭節點 dummy 簡化鏈表操作。
3. 每次迴圈：
   a. 檢查是否有至少 k 個節點可以反轉，若不足則直接返回結果。
   b. 反轉當前 k 個節點（經典單鏈表反轉方法）。
   c. 將上一組的尾節點指向反轉後的新頭節點，並將反轉後尾節點指向下一組的開始節點。
4. 更新指標，繼續處理下一組。
5. 時間複雜度 O(n)，每個節點被訪問一次。
6. 空間複雜度 O(1)，僅使用常數個指標變數。
*/
