package chess.domain.piece;

import chess.domain.position.Position;

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
        if (isNotMovableTo(position, destination)) {
            throw new IllegalArgumentException("기물의 이동 범위에 속하지 않습니다.");
        }
        position = destination;
    }

    public boolean isEnemy(Piece that) {
        return this.team != that.team;
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public boolean isNotSameTeam(Team team) {
        return this.team != team;
    }

    public abstract void canPawnMove(Piece that);

    protected abstract boolean isNotMovableTo(Position start, Position destination);

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name.getName();
    }
}
