package chess.handler;

import chess.handler.exception.AlreadyEndGameException;
import chess.web.dto.DefaultResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.http.HttpStatus;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class EndGameExceptionHandler implements ExceptionHandler<AlreadyEndGameException> {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void handle(AlreadyEndGameException exception, Request request, Response response) {
        response.status(HttpStatus.BAD_REQUEST_400);
        response.body(gson.toJson(DefaultResponse.BADREQUEST(exception, exception.getMessage())));
    }
}
