package chess.dto;

public class ExceptionDto {
    private final boolean ok;
    private final String message;

    public ExceptionDto(String message) {
        this.ok = false;
        this.message = message;
    }

    public boolean isOk() {
        return ok;
    }

    public String getMessage() {
        return message;
    }
}
