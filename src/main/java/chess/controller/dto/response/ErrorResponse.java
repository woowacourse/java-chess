package chess.controller.dto.response;

public class ErrorResponse implements ChessGameResponse {

    private final boolean isOk;
    private final String message;

    public ErrorResponse(String message) {
        this.isOk = false;
        this.message = message;
    }
}
