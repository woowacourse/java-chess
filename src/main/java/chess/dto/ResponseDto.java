package chess.dto;

public class ResponseDto {
    private boolean success = false;
    private String data;
    private String message = "";

    public ResponseDto(boolean success, String data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
