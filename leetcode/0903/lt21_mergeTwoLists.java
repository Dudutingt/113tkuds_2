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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        if (list1 != null) current.next = list1;
        if (list2 != null) current.next = list2;

        return dummy.next;
    }
}

/*
解題思路：
1. 使用一個虛擬頭節點 (dummy) 來簡化操作，最後回傳 dummy.next。
2. 用指標 current 指向新鏈表的末端，每次比較 list1 和 list2 的節點值，將較小的接到 current 後面。
3. 移動被接上的鏈表指針，並把 current 往後移。
4. 當其中一個鏈表走到底，直接把另一個鏈表接到 current 後面。
5. 這樣可以保證結果是有序的，因為兩個輸入鏈表本身都是有序的。
時間複雜度 O(m+n)，空間複雜度 O(1)。
*/
