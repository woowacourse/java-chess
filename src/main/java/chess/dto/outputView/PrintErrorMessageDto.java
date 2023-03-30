package chess.dto.outputView;

public final class PrintErrorMessageDto {
    private final String message;

    public PrintErrorMessageDto(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
