package chess.dto.outputView;

public final class PrintErrorMessageDto {

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private final String message;

    public PrintErrorMessageDto(final String message) {
        this.message = ERROR_MESSAGE_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
