package code.parser;

public class ParserFactory {

    public static Parser getParser(String format) throws Exception {
        switch (format) {
            case "json":
                return new JsonParser();
            case "yml":
            case "yaml":
                return new YmlParser();
            default:
                throw new Exception("No parser found for the format: " + format);
        }
    }
}
