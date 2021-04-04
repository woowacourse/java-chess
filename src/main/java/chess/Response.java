package chess;

import org.eclipse.jetty.http.HttpStatus;

public class Response {
    private final int code;
    private final String status;
    private final Object body;

    public Response(HttpStatus.Code code, Object body) {
        this(code.getCode(), code.getMessage(), body);
    }

    public Response(int code, String status, Object body) {
        this.status = status;
        this.code = code;
        this.body = body;
    }
}
