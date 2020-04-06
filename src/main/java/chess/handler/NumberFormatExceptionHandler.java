package chess.handler;

import chess.service.dto.DefaultResponse;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

public class NumberFormatExceptionHandler extends AbstractExceptionHandler<NumberFormatException> {
    @Override
    public void handle(NumberFormatException exception, Request request, Response response) {
        response.status(HttpStatus.BAD_REQUEST_400);
        response.body(gson.toJson(DefaultResponse.BADREQUEST(exception, request.params("id") + "는 잘못된 id입니다.")));
    }
}
