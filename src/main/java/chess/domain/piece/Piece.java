package chess.domain.piece;

import chess.domain.LocationDiff;
import chess.domain.Direction;

public abstract class Piece {
    private final Team team;
    private final Name name;
    private boolean isFirst;

    public Piece(Team team, Name name) {
        this.team = team;
        this.name = name;
        isFirst = true;
    }

    public String getName() {
        return name.getName(team);
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

    public boolean isPawn() {
        return getClass() == Pawn.class;
    }

    public boolean isKing() {
        return getClass() == King.class;
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void changeNotFirst() {
        isFirst = false;
    }

    public abstract boolean isMovableDirection(Direction direction);

    public abstract boolean isMovableDistance(LocationDiff locationDiff);

    public abstract double getScore();
}
