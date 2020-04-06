package chess.handler;

import chess.web.dto.DefaultResponse;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import java.util.NoSuchElementException;

public class NoSuchElementExceptionHandler extends AbstractExceptionHandler<NoSuchElementException> {

    @Override
    public void handle(NoSuchElementException exception, Request request, Response response) {
        response.status(HttpStatus.BAD_REQUEST_400);
        response.body(gson.toJson(DefaultResponse.BADREQUEST(exception, exception.getMessage())));
    }
}
