package chess.service;

import java.util.HashMap;
import java.util.Map;

public class RequestHandler {
    private static final String SEPARATOR_OF_PARAMETERS = "&";
    private static final String SEPARATOR_OF_NAME_AND_VALUE = "=";
    private static final int INDEX_OF_PARAMETER_NAME = 0;
    private static final int INDEX_OF_PARAMETER_VALUE = 1;

    private RequestHandler() {

    }

    public static Map<String, String> parseRequestQuery(final String requestQuery) {
        final Map<String, String> queryTable = new HashMap<>();

        for (final String parameter : requestQuery.split(SEPARATOR_OF_PARAMETERS)) {
            final String[] info = parameter.split(SEPARATOR_OF_NAME_AND_VALUE);
            final String name = info[INDEX_OF_PARAMETER_NAME];
            final String value = info[INDEX_OF_PARAMETER_VALUE];
            queryTable.put(name, value);
        }

        return queryTable;
    }
}
