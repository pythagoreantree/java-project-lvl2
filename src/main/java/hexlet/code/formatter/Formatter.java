package hexlet.code.formatter;

import java.util.List;
import java.util.Map;

public interface Formatter {

    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";
    public static final String ADDED = "added";
    public static final String DELETED = "deleted";

    String format(List<Map<String, Object>> properties) throws Exception;
}
