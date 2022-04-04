package chess.dto;

public class ResponseDto {

    private final int status;
    private final String errorMessage;
    private final boolean finished;

    private ResponseDto(int status, String errorMessage, boolean finished) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.finished = finished;
    }

    public static ResponseDto of(int status, String errorMessage, boolean finished) {
        return new ResponseDto(status, errorMessage, finished);
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
