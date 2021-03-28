package chess.dto;

public enum StatusResponse {
    SUCCESS ("Success"),
    ERROR ("Error");

    private String status;

    public String getStatus() {
        return status;
    }

    StatusResponse(String status) {
        this.status = status;
    }
}
