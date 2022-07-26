package code.formatter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public final class JsonFormatter implements Formatter {

    @Override
    public String format(List<Map<String, Object>> properties) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(properties);
    }
}
