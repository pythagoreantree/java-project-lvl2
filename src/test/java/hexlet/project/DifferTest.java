package hexlet.project;

import hexlet.code.Differ;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.skyscreamer.jsonassert.JSONParser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Slf4j
@Order(1)
class DifferTest {

    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
            .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = getFixturePath("file1." + format).toString();
        String filePath2 = getFixturePath("file2." + format).toString();

        log.debug("Differ.generate(" + filePath1 + ", " + filePath2 + ")");
        assertThat(Differ.generate(filePath1, filePath2))
            .isEqualTo(resultStylish);

        log.debug(
            "Differ.generate(" + filePath1 + ", " + filePath2 + ", stylish)"
        );
        assertThat(Differ.generate(filePath1, filePath2, "stylish"))
            .isEqualTo(resultStylish);

        log.debug(
            "Differ.generate(" + filePath1 + ", " + filePath2 + ", plain)"
        );
        assertThat(Differ.generate(filePath1, filePath2, "plain"))
            .isEqualTo(resultPlain);

        log.debug(
            "Differ.generate(" + filePath1 + ", " + filePath2 + ", json)"
        );
        String actualJson = Differ.generate(filePath1, filePath2, "json");
        assertDoesNotThrow(() -> JSONParser.parseJSON(actualJson));
        // JSONAssert.assertEquals(resultJson, actualJson, false);
    }
}
