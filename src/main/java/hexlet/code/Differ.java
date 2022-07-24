package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static Map<String, Object> getFileData(String filepath) throws IOException {
        Path absolutePath = Paths.get(filepath).toAbsolutePath();
//        System.out.println(absolutePath.toString());
        File file = new File(absolutePath.toString());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, new TypeReference<Map<String, Object>>() { });
    }
    public static String generate(String filepath1, String filepath2) throws IOException {
        Map<String, Object> mapFirst = getFileData(filepath1);
        Map<String, Object> mapSecond = getFileData(filepath2);

        Set<String> allkeys = new TreeSet<>();
        allkeys.addAll(mapFirst.keySet());
        allkeys.addAll(mapSecond.keySet());
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("}");
        /*for(Map.Entry<String, Object> entry: map.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }*/
        return sb.toString();
    }

    /*for (String key: allkeys) {
        if (mapFirst.containsKey(key) && !mapSecond.containsKey(key)) {
            sb.append("-").append(" ").
                    append(key).append(":").append(mapFirst.get(key)).append("\n");
        } else if (mapFirst.containsKey(key) && mapSecond.containsKey(key)) {
            Object o1 = mapFirst.get(key);
            Object o2 = mapSecond.get(key);
            if (o1.toString().equals(o2.toString())) {
                sb.append("  ").append(key).append(": ").append(o1).append("\n");
            } else {
                sb.append("-").append(" ").
                        append(key).append(": ").append(o1).append("\n");
                sb.append("+").append(" ").
                        append(key).append(": ").append(o2).append("\n");
            }
        } else {
            sb.append("+").append(" ").
                    append(key).append(": ").append(mapSecond.get(key)).append("\n");
        }
    }*/
}
