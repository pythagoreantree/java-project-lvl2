package hexlet.code;

import hexlet.code.parser.Parser;
import hexlet.code.parser.ParserFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        Objects.requireNonNull(filepath1);
        Objects.requireNonNull(filepath2);
        if (filepath1.isEmpty() || filepath2.isEmpty()) {
            throw new Exception("Both file paths should be filled.");
        }

        String[] path = filepath1.split("\\.");
        String format = path.length < 2 ? "json" : path[1];
        Parser parser = ParserFactory.getParser(format);
        Map<String, Object> mapFirst = parser.parse(getFile(filepath1));
        Map<String, Object> mapSecond = parser.parse(getFile(filepath2));

        Set<String> allkeys = new TreeSet<>();
        allkeys.addAll(mapFirst.keySet());
        allkeys.addAll(mapSecond.keySet());
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (String key: allkeys) {
            if (mapFirst.containsKey(key) && !mapSecond.containsKey(key)) {
                sb.append("  ").append("-").append(" ").
                        append(key).append(": ").append(mapFirst.get(key)).append("\n");
            } else if (mapFirst.containsKey(key) && mapSecond.containsKey(key)) {
                Object o1 = mapFirst.get(key);
                Object o2 = mapSecond.get(key);
                if (o1.toString().equals(o2.toString())) {
                    sb.append("  ").append("  ").append(key).append(": ").append(o1).append("\n");
                } else {
                    sb.append("  ").append("-").append(" ").
                            append(key).append(": ").append(o1).append("\n");
                    sb.append("  ").append("+").append(" ").
                            append(key).append(": ").append(o2).append("\n");
                }
            } else {
                sb.append("  ").append("+").append(" ").
                        append(key).append(": ").append(mapSecond.get(key)).append("\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static File getFile(String filepath) {
        Path path = Paths.get(filepath);
        if (path.isAbsolute()) {
            return path.toFile();
        }
        String workingDirectory = System.getProperty("user.dir");
        Path wd = Paths.get(workingDirectory);
        Path finalPath = wd.resolve(path);
        return finalPath.toFile();
    }


}
