package chess.domain.DTO;

public class MoveInfoDTO {
    private String target;
    private String destination;

    public MoveInfoDTO(String target, String destination) {
        this.target = target;
        this.destination = destination;
    }

    public String getTarget() {
        return target;
    }

    public String getDestination() {
        return destination;
    }
}
