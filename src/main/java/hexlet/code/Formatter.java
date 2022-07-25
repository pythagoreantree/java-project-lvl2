package hexlet.code;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";
    public static final String ADDED = "added";
    public static final String DELETED = "deleted";

    public static String formatToString(List<Map<String, Object>> properties) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Map prop : properties) {
            String status = prop.get("status").toString();
            String property = prop.get("property").toString();
            switch (status) {
                case CHANGED:
                    sb.append("  ").append("-").append(" ").
                            append(property).append(": ").append(prop.get("value1")).append("\n");
                    sb.append("  ").append("+").append(" ").
                            append(property).append(": ").append(prop.get("value2")).append("\n");
                    break;
                case UNCHANGED:
                    sb.append("  ").append("  ").append(property)
                            .append(": ").append(prop.get("value")).append("\n");
                    break;
                case ADDED:
                    sb.append("  ").append("+").append(" ").
                            append(property).append(": ").append(prop.get("value")).append("\n");
                    break;
                case DELETED:
                    sb.append("  ").append("-").append(" ").
                            append(property).append(": ").append(prop.get("value")).append("\n");
                    break;
                default:
                    throw new Exception("No such status " + status);
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
