package chess.domain.piece;

public class Piece {

    private final String name;
    private final Team team;

    public Piece(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public String getName() {
        if (team.isWhite()) {
            return name.toLowerCase();
        }
        return name;
    }
}
