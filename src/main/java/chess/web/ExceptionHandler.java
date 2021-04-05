package chess.web;

import spark.Request;
import spark.Response;

public class ExceptionHandler {

    private ExceptionHandler() {
    }

    public static void bindException(RuntimeException runtimeException, Request request, Response response) {
        response.status(500);
        response.type("application/json");
        response.body(runtimeException.getMessage());
    }
}
