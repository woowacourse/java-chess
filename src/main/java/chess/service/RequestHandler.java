package chess.service;

import chess.domain.board.position.Position;
import spark.Request;

import java.util.HashMap;
import java.util.Map;

public class RequestHandler {
    private static final String SEPARATOR_OF_PARAMETERS = "&";
    private static final String SEPARATOR_OF_NAME_AND_VALUE = "=";
    private static final int INDEX_OF_PARAMETER_NAME = 0;
    private static final int INDEX_OF_PARAMETER_VALUE = 1;

    private RequestHandler() {
    }

    public static Map<String, String> parse(final Request request) {
        final Map<String, String> queryTable = new HashMap<>();

        final String requestBody = request.body();
        for (final String parameter : requestBody.split(SEPARATOR_OF_PARAMETERS)) {
            final String[] info = parameter.split(SEPARATOR_OF_NAME_AND_VALUE);
            final String name = info[INDEX_OF_PARAMETER_NAME];
            final String value = info[INDEX_OF_PARAMETER_VALUE];
            queryTable.put(name, value);
        }

        return queryTable;
    }

    public static Position source(final Request req) {
        return new Position(parse(req).get("source"));
    }

    public static Position target(final Request req) {
        return new Position(parse(req).get("target"));
    }

    public static Long roomId(final Request req) {
        return Long.parseLong(req.params(":roomId"));
    }

    public static String roomName(final Request req) {
        return req.params(":roomName");
    }
}
