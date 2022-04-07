package chess.dto;

public class ResponseDto {

    private final int status;
    private final String errorMessage;
    private final boolean finished;

    public ResponseDto(int status, String errorMessage, boolean finished) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.finished = finished;
    }

    public int getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public String toString() {
        return "{" +
                "\"status\" : " + status +
                ", \"errorMessage\" : \"" + errorMessage + "\"" +
                ", \"finished\" : " + finished + "}";
    }
}
