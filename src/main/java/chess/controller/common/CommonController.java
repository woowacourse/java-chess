package chess.controller.common;

import spark.Request;
import spark.Response;

import java.util.Optional;

public class CommonController {
    private CommonController() {
        throw new AssertionError();
    }

    public static <T extends Exception> void handlingException(T exception, Request request, Response response) {
        response.status(500);
        response.body("잘못 입력했습니다.\n" + exception.getMessage());
    }

    public static String nullable(String param) {
        return Optional.ofNullable(param).orElseThrow(IllegalArgumentException::new);
    }
}
