package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class DifferTest {

    private static String resultStylish;
    private static String resultStylishComplex;
    private static String resultPlainComplex;

    private static Path getPath(String name) {
        return Paths.get("src", "test", "resources", name);
    }

    private static String getStringPath(String name) {
        return getPath(name).toString();
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
                return resultPlainComplex;
            default:
                return resultStylishComplex;
        }
    }

    @BeforeAll
    static void loadFiles() {
        resultStylish = getResourceByName("result_stylish.txt");
        resultStylishComplex = getResourceByName("result_stylish_complex.txt");
        resultPlainComplex = getResourceByName("result_plain_complex.txt");
    }

    @DisplayName("Test simple cases")
    @ParameterizedTest(name = "Test {0} input files")
    @ValueSource(strings = {"json", "yml"})
    void testSimple(String ext) {
        try {
            String file1 = getStringPath(String.format("%s/file1.%s", ext, ext));
            String file2 = getStringPath(String.format("%s/file2.%s", ext, ext));
            String ans = Differ.generate(file1, file2);
            Assertions.assertTrue(resultStylish.equals(ans));
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    @DisplayName("Test complex cases")
    @ParameterizedTest(name = "Test {0} input files")
    @CsvSource({ "json", "yml" } )
    void testComplex(String ext) {
        try {
            String file1 = getStringPath(String.format("%s/file3.%s", ext, ext));
            String file2 = getStringPath(String.format("%s/file4.%s", ext, ext));
            String ans = Differ.generate(file1, file2);
            Assertions.assertTrue(resultStylishComplex.equals(ans));
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    @DisplayName("Test different formats")
    @ParameterizedTest(name = "Test output format {0}")
    @CsvSource({"stylish", "plain"})
    void testFormats(String format) {
        try {
            String file1 = getStringPath("json/file3.json");
            String file2 = getStringPath("json/file4.json");
            String ans = Differ.generate(file1, file2, format);
            String res = getResultByFormat(format);
            Assertions.assertTrue(res.equals(ans));
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }
}
