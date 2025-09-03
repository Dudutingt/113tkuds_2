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
    public ListNode swapPairs(ListNode head) {
        // 建立虛擬頭節點，方便處理頭部交換
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // 每次交換兩個節點
        while (head != null && head.next != null) {
            ListNode first = head;
            ListNode second = head.next;

            // 交換節點
            prev.next = second;
            first.next = second.next;
            second.next = first;

            // 更新指針，準備下一組
            prev = first;
            head = first.next;
        }

        return dummy.next;
    }
}

/*
解題思路：
1. 題目要求兩兩交換相鄰節點，不能只交換節點的值，而是要操作指標。
2. 採用虛擬頭節點（dummy）簡化操作：
   - prev 指向當前要交換的兩個節點之前的位置。
   - first 與 second 分別是要交換的兩個節點。
3. 交換流程：
   - prev.next 指向 second
   - first.next 指向 second.next
   - second.next 指向 first
   -> 完成後，節點順序就被交換。
4. 移動 prev 與 head 指標，繼續處理下一組節點。
5. 特殊情況：
   - 當鏈表長度為 0 或 1，直接返回原鏈表。
6. 時間複雜度：O(n)，每個節點最多訪問一次。
7. 空間複雜度：O(1)，僅使用額外的指標變數。
*/
