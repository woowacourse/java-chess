package chess.beforedb.controller.web;

public class MoveResponse {
    private final boolean isMoveError;
    private final String errorMessage;

    public MoveResponse(boolean isMoveError, String errorMessage) {
        this.isMoveError = isMoveError;
        this.errorMessage = errorMessage;
    }

    public MoveResponse(boolean isMoveError) {
        this(isMoveError, null);
    }

    public boolean isMoveError() {
        return isMoveError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
