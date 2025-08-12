import java.util.*;


public class MultiLevelCacheSystem<K, V> {
    static class Level {
        int capacity;
        int cost;
        LinkedHashMap<Object, Object> map; 
        Level(int cap, int c) {
            capacity = cap; cost = c;
            map = new LinkedHashMap<>(16, 0.75f, true);
        }
    }

    private Level L1, L2, L3;
    private Map<K, Integer> freq; 
    private long timestamp;

    public MultiLevelCacheSystem() {
        L1 = new Level(2, 1);
        L2 = new Level(5, 3);
        L3 = new Level(10, 10);
        freq = new HashMap<>();
        timestamp = 0;
    }

    
    public void put(K key, V value) {
        if (L1.map.containsKey(key) || L2.map.containsKey(key) || L3.map.containsKey(key)) {
            
            get(key);
            if (L1.map.containsKey(key)) L1.map.put(key, value);
            else if (L2.map.containsKey(key)) L2.map.put(key, value);
            else L3.map.put(key, value);
            return;
        }
        
        ensureSpace(L3);
        L3.map.put(key, value);
        freq.put(key, 0);
        maybePromote(key);
    }

    public V get(K key) {
        timestamp++;
        if (L1.map.containsKey(key)) {
            freq.put(key, freq.getOrDefault(key,0) + 1);
            return (V)L1.map.get(key);
        } else if (L2.map.containsKey(key)) {
            freq.put(key, freq.getOrDefault(key,0) + 1);
            V val = (V)L2.map.get(key);
            
            maybePromote(key);
            return val;
        } else if (L3.map.containsKey(key)) {
            freq.put(key, freq.getOrDefault(key,0) + 1);
            V val = (V)L3.map.get(key);
            maybePromote(key);
            return val;
        } else {
            return null;
        }
    }

    
    private void maybePromote(K key) {
        int f = freq.getOrDefault(key, 0);
        if (L3.map.containsKey(key)) {
            int score = f - L3.cost;
            if (score >= 1) moveUp(key, L3, L2);
        } else if (L2.map.containsKey(key)) {
            int score = f - L2.cost;
            if (score >= 2) moveUp(key, L2, L1);
        }
        
    }

    private void moveUp(K key, Level from, Level to) {
        Object val = from.map.remove(key);
        ensureSpace(to);
        to.map.put(key, val);
    }

    
    private void ensureSpace(Level lvl) {
        if (lvl.map.size() < lvl.capacity) return;
        // evict eldest (first key)
        Iterator<Map.Entry<Object,Object>> it = lvl.map.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<Object,Object> e = it.next();
            Object evictKey = e.getKey();
            Object evictVal = e.getValue();
            it.remove();
            
            if (lvl == L1) {
                ensureSpace(L2);
                L2.map.put(evictKey, evictVal);
            } else if (lvl == L2) {
                ensureSpace(L3);
                L3.map.put(evictKey, evictVal);
            } else {
                
                freq.remove(evictKey);
            }
        }
    }

    public void debugPrint() {
        System.out.println("L1: " + L1.map.keySet());
        System.out.println("L2: " + L2.map.keySet());
        System.out.println("L3: " + L3.map.keySet());
    }

    
    public static void main(String[] args) {
        MultiLevelCacheSystem<Integer, String> cache = new MultiLevelCacheSystem<>();
        cache.put(1, "A"); cache.put(2, "B"); cache.put(3, "C");
        
        cache.get(1); cache.get(1); cache.get(2);
        cache.debugPrint();
        cache.put(4,"D"); cache.put(5,"E"); cache.put(6,"F");
        cache.debugPrint();
        
    }
}
