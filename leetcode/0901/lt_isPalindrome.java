class Solution {
    public boolean isPalindrome(int x) {
        
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;
        while (x > reversedHalf) {
            int pop = x % 10;
            x /= 10;
            reversedHalf = reversedHalf * 10 + pop;
        }

        return x == reversedHalf || x == reversedHalf / 10;
    }
}
