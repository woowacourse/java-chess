package chess.dto.response;

import chess.dto.responsedto.ResponseDto;

public class Response {
    private final int code;
    private final String message;
    private final ResponseDto data;

    public Response(ResponseCode responseCode) {
        this(responseCode, null);
    }

    public Response(ResponseCode responseCode, ResponseDto data) {
        this(responseCode.getCode(), responseCode.getMessage(), data);
    }

    public Response(int code, String message) {
        this(code, message, null);
    }

    public Response(int code, String message, ResponseDto data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
