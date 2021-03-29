package chess.controller.dto.response;

public class MoveResponseDTOForDB {
    private final boolean isMoveError;
    private final String errorMessage;

    public MoveResponseDTOForDB(boolean isMoveError, String errorMessage) {
        this.isMoveError = isMoveError;
        this.errorMessage = errorMessage;
    }

    public MoveResponseDTOForDB(boolean isMoveError) {
        this(isMoveError, null);
    }

    public boolean isMoveError() {
        return isMoveError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
