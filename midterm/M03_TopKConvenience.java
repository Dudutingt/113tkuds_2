import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        int index; 

        Item(String name, int qty, int index) {
            this.name = name;
            this.qty = qty;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int K = sc.nextInt();

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            items.add(new Item(name, qty, i));
        }

        
        PriorityQueue<Item> heap = new PriorityQueue<>(K, new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (a.qty != b.qty) return a.qty - b.qty; 
                return a.index - b.index; 
            }
        });

        for (Item it : items) {
            if (heap.size() < K) {
                heap.offer(it);
            } else {
                
                Item min = heap.peek();
                if (compareBetter(it, min)) {
                    heap.poll();
                    heap.offer(it);
                }
            }
        }

        List<Item> result = new ArrayList<>(heap);
        result.sort(new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (b.qty != a.qty) return b.qty - a.qty; 
                return a.index - b.index; 
            }
        });

        for (Item it : result) {
            System.out.println(it.name + " " + it.qty);
        }
    }

    static boolean compareBetter(Item a, Item b) {
        if (a.qty != b.qty) return a.qty > b.qty;
        return a.index < b.index; 
    }
}

/*
【時間複雜度分析】
- 讀取輸入：O(n)
- 每次插入 Heap O(log K)，總共 n 次 → O(n log K)
- 最後排序 K 筆 → O(K log K)
=> 總時間複雜度：O(n log K + K log K)，因 K ≪ n，可視為 O(n log K)
【空間複雜度分析】
- 儲存 n 筆資料：O(n)
- Min-Heap 大小 K：O(K)
=> 總空間複雜度：O(n)
*/
