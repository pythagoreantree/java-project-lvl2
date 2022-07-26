package code.formatter;

import java.util.List;
import java.util.Map;

public final class PlainFormatter implements Formatter {

    @Override
    public String format(List<Map<String, Object>> properties) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (Map prop : properties) {
            String status = prop.get("status").toString();
            String property = prop.get("property").toString();
            String line;
            switch (status) {
                case CHANGED:
                    String value1 = getStringValue(prop.get("value1"));
                    String value2 = getStringValue(prop.get("value2"));
                    line = String.format("Property '%s' was updated. From %s to %s", property, value1, value2);
                    sb.append(line).append("\n");
                    break;
                case UNCHANGED:
                    break;
                case ADDED:
                    String value = getStringValue(prop.get("value"));
                    line = String.format("Property '%s' was added with value: %s", property, value);
                    sb.append(line).append("\n");
                    break;
                case DELETED:
                    line = String.format("Property '%s' was removed", property);
                    sb.append(line).append("\n");
                    break;
                default:
                    throw new Exception("No such status " + status);
            }
        }
        return sb.toString();
    }

    private String getStringValue(Object value) {
        if (value == null) {
            return "null";
        }

        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        return value.toString();
    }
}
