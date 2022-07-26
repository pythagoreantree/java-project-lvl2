package hexlet.project;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
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
@Order(2)
class CliTest {

    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    private static Path getFixturePath(String filename) {
        return Paths.get("src", "test", "resources", "fixtures", filename)
            .toAbsolutePath().normalize();
    }

    private static String readFixture(String filename) throws Exception {
        Path filepath = getFixturePath(filename);
        return Files.readString(filepath);
    }

    private static String execCommand(String filePath1,
                                      String filePath2,
                                      String format) throws Exception {
        String commandFormat = format.isEmpty() ? "" : " -f " + format;
        String command = "app/build/install/app/bin/app"
                        + commandFormat
                        + " " + filePath1
                        + " " + filePath2;

        // NOTE: Users need to see the command being run
        log.debug(command);

        Process process = Runtime.getRuntime().exec(command);
        // TODO: Need to refactor, otherwise in the output the message: deprecated
        String output = IOUtils.toString(process.getInputStream());
        process.waitFor();

        return output;
    }

    private static String execCommand(String filePath1,
        String filePath2) throws Exception {
        return execCommand(filePath1, filePath2, "");
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void cliGenerateTest(String format) throws Exception {
        String filePath1 = getFixturePath("file1." + format).toString();
        String filePath2 = getFixturePath("file2." + format).toString();

        String outputDefault = execCommand(filePath1, filePath2);
        assertThat(outputDefault).isEqualTo(resultStylish);

        String outputStylish = execCommand(filePath1, filePath2, "stylish");
        assertThat(outputStylish).isEqualTo(resultStylish);

        String outputPlain = execCommand(filePath1, filePath2, "plain");
        assertThat(outputPlain).isEqualTo(resultPlain);

        String outputJoin = execCommand(filePath1, filePath2, "json");
        assertDoesNotThrow(() -> JSONParser.parseJSON(outputJoin));
        // JSONAssert.assertEquals(resultJson, outputJoin, false);
    }
}
