package hexlet.code.formatter;

public class FormatterFactory {

    public static Formatter getFormatter(String format) {
        switch (format) {
            case "plain":
                return new PlainFormatter();
            case "json":
                return new JsonFormatter();
            default:
                return new StylishFormatter();
        }
    }
}
