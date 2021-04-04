package chess.dto;

public final class PieceDTO {
    private final String team;
    private final String initial;
    private final String position;

    public PieceDTO(String team, String initial, String position) {
        this.team = team;
        this.initial = initial;
        this.position = position;
    }

    public String getTeam() {
        return team;
    }

    public String getInitial() {
        return initial;
    }

    public String getPosition() {
        return position;
    }
}
