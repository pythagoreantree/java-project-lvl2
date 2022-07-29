package hexlet.code;

import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.FormatterFactory;
import hexlet.code.parser.Parser;
import hexlet.code.parser.ParserFactory;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Objects.requireNonNull(filepath1);
        Objects.requireNonNull(filepath2);
        if (filepath1.isEmpty() && filepath2.isEmpty()) {
            throw new Exception("Both filepaths are empty!");
        }

        String fileFormat = FilenameUtils.getExtension(filepath1);
        Parser parser = ParserFactory.getParser(fileFormat);
        Map<String, Object> mapFirst = parser.parse(getContent(filepath1));
        Map<String, Object> mapSecond = parser.parse(getContent(filepath2));

        List<Map<String, Object>> result = Builder.buildTree(mapFirst, mapSecond);

        Formatter formatter = FormatterFactory.getFormatter(format);
        return formatter.format(result);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static Path getPath(String filepath) {
        Path path = Paths.get(filepath);
        if (path.isAbsolute()) {
            return path;
        }
        return path.toAbsolutePath().normalize();
    }

    public static String getContent(String filepath) throws IOException {
        Path path = getPath(filepath);
        return Files.readString(path);
    }

}
