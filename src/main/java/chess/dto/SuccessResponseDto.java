package chess.dto;

public class SuccessResponseDto {

    private final String body;

    public SuccessResponseDto(String body) {
        this.body = body;
    }

    public String toJson() {
        return "{\"ok\":true,"
                + "\"error\":null,"
                + "\"body\":" + body + "}";
    }
}
