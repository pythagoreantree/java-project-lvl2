package hexlet.project;

import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class DifferTest {

    private static String resultSimpleStylish;
    private static String resultStylish;
    private static String resultPlain;
    private static String resultJson;

    private static Path getPath(String name) {
        return Paths.get("src", "test", "resources", name);
    }

    private static String getStringPath(String name) {
        return getPath(name).toString();
    }

    private static String getFile(String name, String ext) {
        return getStringPath(String.format("%s/%s.%s", ext, name, ext));
    }

    private static String getResourceByName(String name) {
        Path path = getPath(name);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file from path " + path.toAbsolutePath());
        }
    }

    private static String getResultByFormat(String format) {
        switch (format) {
            case "plain":
                return resultPlain;
            case "json":
                return resultJson;
            default:
                return resultStylish;
        }
    }

    @BeforeAll
    static void loadFiles() {
        resultSimpleStylish = getResourceByName("result_simple_stylish.txt");
        resultStylish = getResourceByName("result_stylish.txt");
        resultPlain = getResourceByName("result_plain.txt");
        resultJson = getResourceByName("result_json.json");
    }

    @DisplayName("Test simple cases")
    @ParameterizedTest(name = "Test {index}: input [{0}], output [default=stylish]")
    @ValueSource(strings = {"json", "yml"})
    void testSimple(String ext) {
        try {
            String file1 = getFile("simple_1", ext);
            String file2 = getFile("simple_2", ext);
            String ans = Differ.generate(file1, file2);
            Assertions.assertTrue(resultSimpleStylish.equals(ans));
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    @DisplayName("Test complex cases")
    @ParameterizedTest(name = "Test {index}: input [{0}], output [default=stylish]")
    @CsvSource({"json", "yml"})
    void testComplex(String ext) {
        try {
            String file1 = getFile("file1", ext);
            String file2 = getFile("file2", ext);
            String ans = Differ.generate(file1, file2);
            Assertions.assertTrue(resultStylish.equals(ans));
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    @DisplayName("Test input and output formats")
    @ParameterizedTest(name = "Test {index}: input [{0}], output [{1}]")
    @CsvSource({"json, stylish", "json, plain", "yml, stylish", "yml, plain"})
    void testFormats(String inputFormat, String outputFormat) {
        try {
            String file1 = getFile("file1", inputFormat);
            String file2 = getFile("file2", inputFormat);
            String ans = Differ.generate(file1, file2, outputFormat);
            String res = getResultByFormat(outputFormat);
            Assertions.assertTrue(res.equals(ans));
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    @DisplayName("Test json output format")
    @ParameterizedTest(name = "Test {index}: input [{0}], output [{1}]")
    @CsvSource({"json, json", "yml, json"})
    void testJsonOutput(String inputFormat, String outputFormat) {
        try {
            String file1 = getFile("file1", inputFormat);
            String file2 = getFile("file2", inputFormat);
            String ans = Differ.generate(file1, file2, outputFormat);
            String res = getResultByFormat(outputFormat);
            JSONAssert.assertEquals(ans, res, false);
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }
}
