package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

/**
 * Description here.
 */
@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    private static final int EXIT_CODE = 0;
    private static final int ERROR_CODE = 1;

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, paramLabel = "format",
            description = "output format [default: ${DEFAULT-VALUE}]", defaultValue = "stylish")
    private String format = "stylish";

    /**
     * Description here.
     * @return exit code
     * @throws Exception if file not found
     */
    @Override
    public Integer call() {
        try {
            String diff = Differ.generate(filepath1, filepath2, format);
            System.out.println(diff);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_CODE;
        }
        return EXIT_CODE;
    }

    public static void main(String[] args) {
        //Try CI
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
