package chess.service.dto;

public class StatusDto {
    private final String status;

    public StatusDto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
