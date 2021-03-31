package chess.domain.dto;

public enum ResponseStatus {
    OK("200"),
    ERROR("400");

    private final String status;

    ResponseStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
