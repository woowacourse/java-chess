package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {

    private final String name;
    protected final Team team;

    public Piece(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    public abstract void movable(Position to, Position from);

    public abstract Direction findDirection(Position from, Position to);

    public String getName() {
        return team.convert(name);
    }

    public boolean isSameTeam(Piece piece) {
        return piece.team == this.team;
    }

    public boolean isSameTeam(Team team) {
        return team == this.team;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "name='" + name + '\'' +
                ", team=" + team +
                '}';
    }
}
