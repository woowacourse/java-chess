package chess.handler;

import chess.handler.exception.SqlExecuteException;
import chess.service.dto.DefaultResponse;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

public class SqlExeptionHandler extends AbstractExceptionHandler<SqlExecuteException> {
    @Override
    public void handle(SqlExecuteException exception, Request request, Response response) {
        response.status((HttpStatus.Code.UNPROCESSABLE_ENTITY.getCode()));
        response.body(gson.toJson(new DefaultResponse<>(HttpStatus.Code.UNPROCESSABLE_ENTITY, exception, exception.getMessage())));
    }
}
