package hexlet.code.parser;

public class ParserFactory {

    public static Parser getParser(String format) throws Exception {
        switch(format) {
            case "json":
                return new JsonParser();
            case "yml":
            case "yaml":
                return new YmlParser();
        }
        throw new Exception("No parser found for the format: " + format);
    }
}
