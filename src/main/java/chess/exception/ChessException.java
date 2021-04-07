package chess.exception;

import chess.dto.response.ResponseCode;

public class ChessException extends RuntimeException {
    private final ResponseCode responseCode;

    public ChessException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

    public int getCode() {
        return responseCode.getCode();
    }
}
