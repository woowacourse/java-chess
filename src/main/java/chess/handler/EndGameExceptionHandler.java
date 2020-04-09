package chess.handler;

import chess.handler.exception.AlreadyEndGameException;
import chess.service.dto.DefaultResponse;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

public class EndGameExceptionHandler extends AbstractExceptionHandler<AlreadyEndGameException> {

    @Override
    public void handle(AlreadyEndGameException exception, Request request, Response response) {
        response.status(HttpStatus.BAD_REQUEST_400);
        response.body(gson.toJson(DefaultResponse.BADREQUEST(exception, exception.getMessage())));
    }
}
