package hexlet.code;

import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class Differ implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 0;
    }
}
