import java.util.HashSet;

public class AdvancedStringRecursion {

    public static void permutations(String s) {
        permutationsHelper("", s);
    }

    private static void permutationsHelper(String prefix, String s) {
        if (s.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < s.length(); i++) {
                permutationsHelper(prefix + s.charAt(i), s.substring(0, i) + s.substring(i + 1));
            }
        }
    }

    public static boolean stringMatch(String text, String pattern) {
        if (pattern.length() == 0) return true;
        if (text.length() < pattern.length()) return false;
        if (text.substring(0, pattern.length()).equals(pattern)) return true;
        return stringMatch(text.substring(1), pattern);
    }

    public static String removeDuplicates(String s) {
        return removeDuplicatesHelper(s, new HashSet<>(), 0);
    }

    private static String removeDuplicatesHelper(String s, HashSet<Character> seen, int index) {
        if (index == s.length()) return "";
        char c = s.charAt(index);
        if (seen.contains(c)) {
            return removeDuplicatesHelper(s, seen, index + 1);
        } else {
            seen.add(c);
            return c + removeDuplicatesHelper(s, seen, index + 1);
        }
    }

    public static void allSubstrings(String s) {
        allSubstringsHelper(s, 0, "");
    }

    private static void allSubstringsHelper(String s, int index, String current) {
        if (index == s.length()) {
            System.out.println(current);
            return;
        }
        
        allSubstringsHelper(s, index + 1, current + s.charAt(index));
        
        allSubstringsHelper(s, index + 1, current);
    }


    public static void main(String[] args) {
        System.out.println("Permutations of 'abc':");
        permutations("abc");

        System.out.println("\nString match 'hello' in 'hello world': " + stringMatch("hello world", "hello"));
        System.out.println("String match 'world' in 'hello': " + stringMatch("hello", "world"));

        System.out.println("\nRemove duplicates from 'banana': " + removeDuplicates("banana"));

        System.out.println("\nAll substrings of 'ab':");
        allSubstrings("ab");
    }
}
