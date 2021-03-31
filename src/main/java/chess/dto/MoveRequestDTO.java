package chess.dto;

public class MoveRequestDTO {

    private final String current;
    private final String destination;
    private final String teamType;

    public MoveRequestDTO(String current, String destination, String teamType) {
        this.current = current;
        this.destination = destination;
        this.teamType = teamType;
    }

    public String getCurrent() {
        return current;
    }

    public String getDestination() {
        return destination;
    }

    public String getTeamType() {
        return teamType;
    }
}
