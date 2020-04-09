package chess.handler;

import chess.service.dto.DefaultResponse;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

public class CanNotMoveExceptionHandler extends AbstractExceptionHandler<IllegalArgumentException> {

    @Override
    public void handle(IllegalArgumentException exception, Request request, Response response) {
        response.status(HttpStatus.BAD_REQUEST_400);
        response.body(gson.toJson(DefaultResponse.BADREQUEST(exception, exception.getMessage())));
    }
}
