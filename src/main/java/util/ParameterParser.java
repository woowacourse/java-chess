package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ParameterParser {
    public static Map<Integer, String> parsingParameterFromCommand(final List<String> command) {
        Map<Integer, String> parameters = new HashMap<>();
        for (int i = 1; i < command.size(); i++) {
            parameters.put(i, command.get(i));
        }
        return parameters;
    }
}
