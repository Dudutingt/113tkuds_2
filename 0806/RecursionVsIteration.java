public class RecursionVsIteration {

    public static long binomialRecursive(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialRecursive(n - 1, k - 1) + binomialRecursive(n - 1, k);
    }

    public static long binomialIterative(int n, int k) {
        long res = 1;
        if (k > n - k) k = n - k;
        for (int i = 0; i < k; i++) {
            res = res * (n - i) / (i + 1);
        }
        return res;
    }

    public static long productRecursive(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * productRecursive(arr, index + 1);
    }

    public static long productIterative(int[] arr) {
        long prod = 1;
        for (int v : arr) prod *= v;
        return prod;
    }

    
    public static int countVowelsRecursive(String s, int index) {
        if (index == s.length()) return 0;
        char c = Character.toLowerCase(s.charAt(index));
        int count = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ? 1 : 0;
        return count + countVowelsRecursive(s, index + 1);
    }

    public static int countVowelsIterative(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) count++;
        }
        return count;
    }


    public static boolean checkParenthesesRecursive(String s) {
        return checkHelper(s, 0, 0);
    }

    private static boolean checkHelper(String s, int index, int count) {
        if (count < 0) return false;
        if (index == s.length()) return count == 0;
        char c = s.charAt(index);
        if (c == '(') count++;
        else if (c == ')') count--;
        return checkHelper(s, index + 1, count);
    }

  
    public static boolean checkParenthesesIterative(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }

    
    public static void main(String[] args) {
        System.out.println("Binomial (5,2) recursive: " + binomialRecursive(5, 2));
        System.out.println("Binomial (5,2) iterative: " + binomialIterative(5, 2));

        int[] arr = {2, 3, 4};
        System.out.println("Product recursive: " + productRecursive(arr, 0));
        System.out.println("Product iterative: " + productIterative(arr));

        String testStr = "Hello World";
        System.out.println("Vowels recursive: " + countVowelsRecursive(testStr, 0));
        System.out.println("Vowels iterative: " + countVowelsIterative(testStr));

        String parenStr1 = "(())()";
        String parenStr2 = "(()))(";
        System.out.println("Check parentheses recursive (\"(())()\"): " + checkParenthesesRecursive(parenStr1));
        System.out.println("Check parentheses iterative (\"(()))(\"): " + checkParenthesesIterative(parenStr2));
    }
}
