import java.util.List;

public class RecursiveTreePreview {

    static class Folder {
        String name;
        List<Object> contents; 

        Folder(String name, List<Object> contents) {
            this.name = name;
            this.contents = contents;
        }
    }

    static class File {
        String name;
        File(String name) { this.name = name; }
    }

    public static int countFiles(Object obj) {
        if (obj instanceof File) {
            return 1;
        } else if (obj instanceof Folder) {
            int sum = 0;
            Folder folder = (Folder) obj;
            for (Object content : folder.contents) {
                sum += countFiles(content);
            }
            return sum;
        }
        return 0;
    }

    public static void printMenu(Object menu, int level) {
        if (menu instanceof String) {
            for (int i = 0; i < level; i++) System.out.print("  ");
            System.out.println(menu);
        } else if (menu instanceof List) {
            List<?> list = (List<?>) menu;
            for (Object item : list) {
                printMenu(item, level + 1);
            }
        }
    }

   
    public static void flattenList(Object obj, List<Integer> result) {
        if (obj instanceof Integer) {
            result.add((Integer) obj);
        } else if (obj instanceof List) {
            for (Object item : (List<?>) obj) {
                flattenList(item, result);
            }
        }
    }

    public static int maxDepth(Object obj) {
        if (!(obj instanceof List)) return 0;
        int max = 0;
        for (Object item : (List<?>) obj) {
            int depth = maxDepth(item);
            if (depth > max) max = depth;
        }
        return max + 1;
    }
}
