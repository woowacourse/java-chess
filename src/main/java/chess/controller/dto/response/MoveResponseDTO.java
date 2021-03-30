package chess.controller.dto.response;

public class MoveResponseDTO {
    private final boolean isMoveError;
    private final String errorMessage;

    public MoveResponseDTO(boolean isMoveError, String errorMessage) {
        this.isMoveError = isMoveError;
        this.errorMessage = errorMessage;
    }

    public MoveResponseDTO(boolean isMoveError) {
        this(isMoveError, null);
    }

    public boolean isMoveError() {
        return isMoveError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
