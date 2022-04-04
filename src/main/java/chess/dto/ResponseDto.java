package chess.dto;

public class ResponseDto {
    private final int status;
    private final String errorMessage;

    public ResponseDto(int status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "{" +
                "\"status\" : " + status +
                ", \"errorMessage\" : \"" + errorMessage + "\""
                + "}";
    }
}
