package hexlet.code.formatter;

public class FormatterFactory {

    public static Formatter getFormatter(String format) {
        switch (format) {
            case "plain":
                return new PlainFormatter();
            default:
                return new StylishFormatter();
        }
    }
}
