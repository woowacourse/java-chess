package chess.domain.dto;

public class ResponseDto {
    private final int status;
    private final String message;

    public ResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public String convertToString() {
        return "{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
