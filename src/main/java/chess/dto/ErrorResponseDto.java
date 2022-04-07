package chess.dto;

public class ErrorResponseDto {

    private final String status;
    private final String message;

    public ErrorResponseDto(final String message) {
        this.status = "error";
        this.message = message;
    }
}
