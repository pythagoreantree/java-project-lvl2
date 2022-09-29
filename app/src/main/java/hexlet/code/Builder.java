package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Builder {

    public static List<Map<String, Object>> buildTree(Map<String, Object> mapFirst, Map<String, Object> mapSecond) {
        Set<String> allkeys = new TreeSet<>();
        allkeys.addAll(mapFirst.keySet());
        allkeys.addAll(mapSecond.keySet());

        List<Map<String, Object>> result = new ArrayList<>();
        for (String key : allkeys) {
            Map<String, Object> propMap = new HashMap<>();
            propMap.put("property", key);
            if (mapFirst.containsKey(key) && mapSecond.containsKey(key)) {
                //values are the same
                if (isEqual(mapFirst.get(key), mapSecond.get(key))) {
                    propMap.put("status", "unchanged");
                    propMap.put("value", mapFirst.get(key));
                } else {
                    //values are different
                    propMap.put("status", "changed");
                    propMap.put("value1", mapFirst.get(key));
                    propMap.put("value2", mapSecond.get(key));
                }
            } else if (mapFirst.containsKey(key) && !mapSecond.containsKey(key)) {
                //second map does not contain value from the first one
                propMap.put("status", "deleted");
                propMap.put("value", mapFirst.get(key));
            } else if (!mapFirst.containsKey(key) && mapSecond.containsKey(key)) {
                //first map does not contain value from the second one
                propMap.put("status", "added");
                propMap.put("value", mapSecond.get(key));
            }
            result.add(propMap);
        }
        return result;
    }

    private static boolean isEqual(Object o1, Object o2) {
        if (o1 == null && o2 == null) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        if (o1.equals(o2)) {
            return true;
        }
        return false;
    }
}
