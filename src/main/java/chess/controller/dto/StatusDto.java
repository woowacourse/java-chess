package chess.controller.dto;

public class StatusDto {
    private final String status;

    public StatusDto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
