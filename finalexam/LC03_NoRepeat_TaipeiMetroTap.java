

import java.io.*;
import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null || s.isEmpty()) {
            System.out.println(0);
            return;
        }

        Map<Character, Integer> last = new HashMap<>();
        int left = 0, ans = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (last.containsKey(c) && last.get(c) >= left) {
                left = last.get(c) + 1;
            }

            last.put(c, i); 
            ans = Math.max(ans, i - left + 1); 
        }

        System.out.println(ans);
    }
}

