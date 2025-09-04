import java.util.*;

public class LC28_StrStr_NoticeSearch {
    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i + m <= n; i++) {
            if (haystack.substring(i, i + m).equals(needle)) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String haystack = sc.nextLine();
        String needle = sc.nextLine();
        System.out.println(strStr(haystack, needle));
    }
}
