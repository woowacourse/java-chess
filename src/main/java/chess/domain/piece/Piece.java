package chess.domain.piece;

public abstract class Piece {
    private final Team team;
    private final Name name;

    public Piece(Team team, Name name) {
        this.team = team;
        this.name = name;
    }

    public String getName() {
        return name.getName(team);
    }
}
