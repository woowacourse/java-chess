package chess;

import org.eclipse.jetty.http.HttpStatus;

public class ChessResponse {
    private final int code;
    private final String status;
    private final Object content;

    public ChessResponse(HttpStatus.Code code, Object content) {
        this(code.getCode(), code.getMessage(), content);
    }

    public ChessResponse(int code, String status, Object content) {
        this.status = status;
        this.code = code;
        this.content = content;
    }

    public static class Ok extends ChessResponse {
        public Ok(Object content) {
            super(HttpStatus.Code.OK, content);
        }
    }

    public static class Created extends ChessResponse {
        public Created(Object content) {
            super(HttpStatus.Code.CREATED, content);
        }
    }
}
