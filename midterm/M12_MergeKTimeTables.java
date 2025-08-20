import java.io.*;
import java.util.*;

public class M12_MergeKTimeTables {
    static class Node {
        int time;   
        int li;     
        int idx;    
        Node(int t, int l, int i) { time = t; li = l; idx = i; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

       
        while ((line = br.readLine()) != null && line.trim().isEmpty()) {}
        if (line == null) return;
        int K = Integer.parseInt(line.trim());

        List<int[]> lists = new ArrayList<>(K);
        boolean isHHmm = false; 

        for (int k = 0; k < K; k++) {
            
            String lenLine;
            while ((lenLine = br.readLine()) != null && lenLine.trim().isEmpty()) {}
            if (lenLine == null) return;
            int len = Integer.parseInt(lenLine.trim());

            int[] arr = new int[len];
            int filled = 0;
            while (filled < len && (line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                StringTokenizer st = new StringTokenizer(line);
                while (st.hasMoreTokens() && filled < len) {
                    String tok = st.nextToken();
                    if (tok.indexOf(':') >= 0) {
                        isHHmm = true;
                        arr[filled++] = parseHHmm(tok);
                    } else {
                        arr[filled++] = Integer.parseInt(tok);
                    }
                }
            }
            lists.add(arr);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override public int compare(Node a, Node b) {
                return Integer.compare(a.time, b.time);
            }
        });

        for (int i = 0; i < K; i++) {
            int[] arr = lists.get(i);
            if (arr.length > 0) pq.offer(new Node(arr[0], i, 0));
        }

        StringBuilder out = new StringBuilder();
        boolean first = true;

        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (!first) out.append(' ');
            first = false;
            out.append(isHHmm ? formatHHmm(cur.time) : Integer.toString(cur.time));

            int[] arr = lists.get(cur.li);
            int nextIdx = cur.idx + 1;
            if (nextIdx < arr.length) {
                pq.offer(new Node(arr[nextIdx], cur.li, nextIdx));
            }
        }

        System.out.println(out.toString());
    }

    private static int parseHHmm(String s) {
        int h = Integer.parseInt(s.substring(0, 2));
        int m = Integer.parseInt(s.substring(3, 5));
        return h * 60 + m;
    }

    private static String formatHHmm(int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }
}