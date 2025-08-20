import java.io.*;

public class M06_PalindromeClean {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) return;

        int l = 0, r = s.length() - 1;
        boolean ok = true;

        while (l < r) {
            
            while (l < r && !Character.isLetter(s.charAt(l))) l++;
            
            while (l < r && !Character.isLetter(s.charAt(r))) r--;

            if (l < r) {
                char cl = Character.toLowerCase(s.charAt(l));
                char cr = Character.toLowerCase(s.charAt(r));
                if (cl != cr) {
                    ok = false;
                    break;
                }
                l++;
                r--;
            }
        }

        System.out.println(ok ? "Yes" : "No");
    }
}