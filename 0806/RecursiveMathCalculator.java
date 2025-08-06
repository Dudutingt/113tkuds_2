public class RecursiveMathCalculator {

    
    public static long combination(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

   
    public static long catalan(int n) {
        if (n == 0) return 1;
        long result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    
    public static long hanoi(int n) {
        if (n == 1) return 1;
        return 2 * hanoi(n - 1) + 1;
    }

    
    public static boolean isPalindrome(int num) {
        return isPalindromeHelper(String.valueOf(num), 0, String.valueOf(num).length() - 1);
    }

    private static boolean isPalindromeHelper(String s, int left, int right) {
        if (left >= right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return isPalindromeHelper(s, left + 1, right - 1);
    }

    
    public static void main(String[] args) {
        System.out.println("C(5,2) = " + combination(5, 2));        // 10
        System.out.println("Catalan(4) = " + catalan(4));             // 14
        System.out.println("Hanoi(3) steps = " + hanoi(3));           // 7
        System.out.println("Is 12321 palindrome? " + isPalindrome(12321)); // true
        System.out.println("Is 12345 palindrome? " + isPalindrome(12345)); // false
    }
}
