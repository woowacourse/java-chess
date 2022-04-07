package chess.dto;

public class ExceptionResponseDto {

    private final String error;

    public ExceptionResponseDto(String errorMessage) {
        this.error = errorMessage;
    }

    public String toJson() {
        return "{\"ok\":false,"
                + "\"error\":\"" + error + "\","
                + "\"body\":null}";
    }
}
