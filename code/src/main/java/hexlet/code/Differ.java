package code;

import code.parser.Parser;
import code.parser.ParserFactory;
import code.formatter.Formatter;
import code.formatter.FormatterFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Objects.requireNonNull(filepath1);
        Objects.requireNonNull(filepath2);
        if (filepath1.isEmpty() && filepath2.isEmpty()) {
            throw new Exception("Both filepaths are empty!");
        }

        //I dislike format parsing
        String[] path = filepath1.split("\\.");
        String fileFormat = path.length < 2 ? "" : path[path.length - 1];
        Parser parser = ParserFactory.getParser(fileFormat);
        Map<String, Object> mapFirst = parser.parse(getFile(filepath1));
        Map<String, Object> mapSecond = parser.parse(getFile(filepath2));

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

        Formatter formatter = FormatterFactory.getFormatter(format);
        return formatter.format(result);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
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

    public static File getFile(String filepath) {
        Path path = Paths.get(filepath);
        if (path.isAbsolute()) {
            return path.toFile();
        }
        return path.toAbsolutePath().normalize().toFile();
    }

}
