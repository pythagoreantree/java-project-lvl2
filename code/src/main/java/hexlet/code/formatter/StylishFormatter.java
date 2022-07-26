package code.formatter;

import java.util.List;
import java.util.Map;

public final class StylishFormatter implements Formatter {

    private static final String INDENT = "  ";

    public String format(List<Map<String, Object>> properties) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Map prop : properties) {
            String status = prop.get("status").toString();
            String property = prop.get("property").toString();
            sb.append(INDENT);
            switch (status) {
                case CHANGED:
                    sb.append("-").append(" ").
                            append(property).append(": ").append(prop.get("value1")).append("\n");
                    sb.append(INDENT);
                    sb.append("+").append(" ").
                            append(property).append(": ").append(prop.get("value2")).append("\n");
                    break;
                case UNCHANGED:
                    sb.append(INDENT).append(property)
                            .append(": ").append(prop.get("value")).append("\n");
                    break;
                case ADDED:
                    sb.append("+").append(" ").
                            append(property).append(": ").append(prop.get("value")).append("\n");
                    break;
                case DELETED:
                    sb.append("-").append(" ").
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
