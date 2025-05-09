package utilities.adapter;

import utilities.adapter.impl.CommaAccountParser;
import utilities.adapter.impl.DoublePipeAccountParser;
import utilities.adapter.impl.PipeAccountParser;

public class AccountParserFactory {

    public static AccountParser getParser(String line) {
        if (line.contains("||")) return new DoublePipeAccountParser();
        if (line.contains("|")) return new PipeAccountParser();
        if (line.contains(",")) return new CommaAccountParser();
        throw new IllegalArgumentException("Formato de cuenta no soportado: " + line);
    }

}
