package chess.domain.piece;

import chess.domain.board.LocationDiff;
import chess.domain.board.Direction;

public abstract class Piece {
    protected final Team team;
    private final Name name;
    private boolean isFirst;

    public Piece(Team team, Name name) {
        this.team = team;
        this.name = name;
        isFirst = true;
    }

    public boolean isBlack() {
        return team.isBlack();
    }

    public boolean isWhite() {
        return team.isWhite();
    }

    public boolean isEmpty() {
        return team.isNone();
    }

    public boolean isFirst() {
        return isFirst;
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public void changeNotFirst() {
        isFirst = false;
    }

    public String getName() {
        return name.getName(team);
    }

    public abstract boolean isPawn();

    public abstract boolean isKing();

    public abstract boolean isMovableDirection(Direction direction);

    public abstract boolean isMovableDistance(LocationDiff locationDiff);

    public abstract double getScore();

    public abstract void checkPawnMovable(Direction direction, Piece targetPiece);
}
