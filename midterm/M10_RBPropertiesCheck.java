import java.io.*;
import java.util.*;

public class M10_RBPropertiesCheck {

    static final int NIL_BLACK_HEIGHT = 1;  
    static final int BH_MISMATCH = Integer.MIN_VALUE; 

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while ((line = br.readLine()) != null && line.trim().isEmpty()) {}
        if (line == null) return;
        int n = Integer.parseInt(line.trim());

        int[] val = new int[n];
        char[] col = new char[n];

        int filled = 0;
        StringTokenizer st = null;
        while (filled < n) {
            line = br.readLine();
            if (line == null) break;
            if (line.trim().isEmpty()) continue;
            st = new StringTokenizer(line);
            while (st.hasMoreTokens() && filled < n) {
                
                int v = Integer.parseInt(st.nextToken());
               
                String cTok;
                
                if (!st.hasMoreTokens()) {
                    String extra;
                    do {
                        extra = br.readLine();
                        if (extra == null) break;
                    } while (extra.trim().isEmpty());
                    if (extra == null) { cTok = "B"; } 
                    else {
                        StringTokenizer st2 = new StringTokenizer(extra);
                        cTok = st2.hasMoreTokens() ? st2.nextToken() : "B";
                        
                    }
                } else {
                    cTok = st.nextToken();
                }
                char c = Character.toUpperCase(cTok.charAt(0));

                if (v == -1) c = 'B';

                val[filled] = v;
                col[filled] = c;
                filled++;
            }
        }

        if (n == 0 || val[0] == -1) {
            System.out.println("RB Valid");
            return;
        }

        if (col[0] != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        for (int i = 1; i < n; i++) {
            if (val[i] == -1) continue;
            int p = (i - 1) / 2;
            
            if (p >= 0 && p < n && val[p] != -1) {
                if (col[i] == 'R' && col[p] == 'R') {
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
            }
        }

        int bh = blackHeight(0, val, col, n);
        if (bh == BH_MISMATCH) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        System.out.println("RB Valid");
    }

    private static int blackHeight(int idx, int[] val, char[] col, int n) {
        
        if (idx >= n) return NIL_BLACK_HEIGHT;

        if (val[idx] == -1) return NIL_BLACK_HEIGHT;

        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        int lb = blackHeight(left, val, col, n);
        if (lb == BH_MISMATCH) return BH_MISMATCH;

        int rb = blackHeight(right, val, col, n);
        if (rb == BH_MISMATCH) return BH_MISMATCH;

        if (lb != rb) return BH_MISMATCH;

        return lb + (col[idx] == 'B' ? 1 : 0);
    }
}