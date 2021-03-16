package chess.domain.piece;

public abstract class Piece implements MoveStrategy {
    private final String name;
    private final Team team;

    public Piece(String name, Team team) {
        this.team = team;
        this.name = convertName(name, team);
    }

    private String convertName(String name, Team team) {
        if (team.isBlack()) {
            return name;
        }
        return name.toLowerCase();
    }

    public String getName() {
        return name;
    }
}
