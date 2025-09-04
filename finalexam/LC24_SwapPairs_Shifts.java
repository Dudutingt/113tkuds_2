import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class LC24_SwapPairs_Shifts {
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (head != null && head.next != null) {
            ListNode a = head, b = head.next;
            prev.next = b;
            a.next = b.next;
            b.next = a;
            prev = a;
            head = a.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListNode list = buildList(sc);
        ListNode swapped = swapPairs(list);
        printList(swapped);
    }

    private static ListNode buildList(Scanner sc) {
        ListNode dummy = new ListNode(0), tail = dummy;
        while (sc.hasNextInt()) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }
        return dummy.next;
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? " " : ""));
            head = head.next;
        }
    }
}
