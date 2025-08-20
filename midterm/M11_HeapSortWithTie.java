import java.io.*;
import java.util.*;

public class M11_HeapSortWithTie {
    static class P {
        int score;
        int idx;
        P(int s, int i) { score = s; idx = i; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while ((line = br.readLine()) != null && line.trim().isEmpty()) {}
        if (line == null) return;
        int n = Integer.parseInt(line.trim());

        P[] a = new P[n];
        int filled = 0;
        while (filled < n && (line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens() && filled < n) {
                int s = Integer.parseInt(st.nextToken());
                a[filled] = new P(s, filled); 
                filled++;
            }
        }

        heapSort(a);

        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i].score);
        }
        System.out.println(sb.toString());
    }


    private static void heapSort(P[] a) {
        int n = a.length;
        buildMaxHeap(a, n);
        for (int end = n - 1; end > 0; end--) {
            swap(a, 0, end);          
            siftDown(a, 0, end);      
        }
        
    }

    private static void buildMaxHeap(P[] a, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) siftDown(a, i, n);
    }

    private static void siftDown(P[] a, int i, int size) {
        while (true) {
            int left = i * 2 + 1;
            if (left >= size) break;
            int right = left + 1;
            int best = left;
            if (right < size && greater(a[right], a[left])) best = right;
            if (greater(a[best], a[i])) {
                swap(a, i, best);
                i = best;
            } else break;
        }
    }

    private static boolean greater(P x, P y) {
        if (x.score != y.score) return x.score > y.score;
        return x.idx > y.idx;
    }

    private static void swap(P[] a, int i, int j) {
        P t = a[i]; a[i] = a[j]; a[j] = t;
    }
}
/*
 * Time Complexity: O(n log n)
 * 說明：先以 Bottom-up 建立 Max-Heap，建堆為 O(n)；接著做 n-1 次 extract-max，
 *       每次下濾為 O(log n)，總計 O(n log n)。額外空間 O(1)（就地排序）。
 * 平手規則（重要）：
 *   以 (score, index) 比較；建立「Max-Heap」時定義：
 *     A 比 B 大 ⇔ (A.score > B.score) 或 (A.score == B.score 且 A.index > B.index)
 *   如此在從尾端回填時（由大到小放右邊），同分者「索引較大者」先被放到更右邊，
 *   因而最終遞增輸出時，左到右會是「索引較小者」在前，符合題意。
 */