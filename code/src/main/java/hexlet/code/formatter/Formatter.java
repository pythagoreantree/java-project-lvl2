package code.formatter;

import java.util.List;
import java.util.Map;

public interface Formatter {

    String CHANGED = "changed";
    String UNCHANGED = "unchanged";
    String ADDED = "added";
    String DELETED = "deleted";

    String format(List<Map<String, Object>> properties) throws Exception;
}
