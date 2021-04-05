package chess;

import org.eclipse.jetty.http.HttpStatus;

import chess.domain.chess.Chess;

public class ChessResponse {
    private final int code;
    private final String status;
    private final Object body;

    public ChessResponse(HttpStatus.Code code, Object body) {
        this(code.getCode(), code.getMessage(), body);
    }

    public ChessResponse(int code, String status, Object body) {
        this.status = status;
        this.code = code;
        this.body = body;
    }

    public static class Ok extends ChessResponse {
        public Ok(Object body) {
            super(HttpStatus.Code.OK, body);
        }
    }

    public static class Created extends ChessResponse {
        public Created(Object body) {
            super(HttpStatus.Code.CREATED, body);
        }
    }
}
