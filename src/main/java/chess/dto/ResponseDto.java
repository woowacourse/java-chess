package chess.dto;

public class ResponseDto {
    private final int status;
    private final String errorMessage;
    private final boolean isGameOver;

    public ResponseDto(final int status, final String errorMessage, final boolean isGameOver) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.isGameOver = isGameOver;
    }

    @Override
    public String toString() {
        return "{" +
                "\"status\" : " + status +
                ", \"errorMessage\" : \"" + errorMessage + "\"" +
                ", \"isGameOver\" : " + isGameOver + "}";
    }
}
