package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {

    private final String name;
    protected final Team team;

    public Piece(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    public abstract boolean movable(Position to, Position from);

    public abstract Direction findDirection(Position from, Position to);

    public String getName() {
        return team.convert(name);
    }

    public boolean isNotSameTeam(Piece piece) {
        return piece.team != this.team;
    }

    protected boolean isSameTeam(Team team) {
        return team == this.team;
    }
}
