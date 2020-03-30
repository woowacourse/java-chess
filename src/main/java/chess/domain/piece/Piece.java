package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

public abstract class Piece {
    protected Position position;
    protected final Name name;
    protected final Team team;

    public Piece(Position position, Name name, Team team) {
        this.position = position;
        this.name = name;
        this.team = team;
    }

    public void moveTo(Position destination) {
        position = destination;
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public boolean isNotSameTeam(Team team) {
        return this.team != team;
    }

    public abstract boolean canNotMoveTo(Piece that);

    protected abstract List<Position> createMovableArea();

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name.getName();
    }
}
