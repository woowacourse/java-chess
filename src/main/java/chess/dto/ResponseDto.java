package chess.dto;

public class ResponseDto {

    private final int status;
    private final String errorMessage;
    private final boolean isRunning;

    public ResponseDto(final int status, final String errorMessage, final boolean isRunning) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.isRunning = isRunning;
    }

    public int getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public String toString() {
        return "{" +
                "\"status\" : " + status +
                ", \"errorMessage\" : \"" + errorMessage + "\"" +
                ", \"isRunning\" : " + isRunning + "}";
    }
}
