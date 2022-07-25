package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class DifferTest {

    private static String resultStylish;
    private static String resultStylishComplex;

    private static String getResourceByName(String name) {
        Path path = Paths.get("src","test", "resources", name);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            System.out.println("Can't read file from path " + path.toAbsolutePath());
        }
        return "";
    }

    @BeforeAll
    static void loadFiles() {
        resultStylish = getResourceByName("result_stylish.txt");
        resultStylishComplex = getResourceByName("result_stylish_complex.txt");
    }

    @DisplayName("Test simple cases")
    @ParameterizedTest(name= "Test {0} input files")
    @ValueSource(strings = { "json", "yml" } )
    void testSimple(String ext) {
        try {
            String ans = Differ.generate("file1." + ext, "file2." + ext);
            Assertions.assertTrue(resultStylish.equals(ans));
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    @DisplayName("Test complex cases")
    @ParameterizedTest(name= "Test {0} input files")
    @ValueSource(strings = { "json", "yml" } )
    void testComplex(String ext) {
        try {
            String ans = Differ.generate("file3." + ext, "file4." + ext);
            Assertions.assertTrue(resultStylishComplex.equals(ans));
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }
}
