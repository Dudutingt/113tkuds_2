import java.util.HashSet;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLen = 0; 
        int left = 0;  
        HashSet<Character> set = new HashSet<>(); 

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);

            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(c);

          
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
