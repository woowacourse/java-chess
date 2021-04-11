package chess.dto;

public class ResponseDTO {
    private boolean success = false;
    private String data;
    private String message = "";

    public ResponseDTO(boolean success, String data, String message) {
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
