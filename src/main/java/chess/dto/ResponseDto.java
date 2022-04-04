package chess.dto;

public class ResponseDto {

    private final int status;
    private final String errorMessage;

    private ResponseDto(int status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public static ResponseDto of(int status, String errorMessage) {
        return new ResponseDto(status, errorMessage);
    }

    public int getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "{" +
                "\"status\" : " + status +
                ", \"errorMessage\" : \"" + errorMessage + "\"}";
    }
}
