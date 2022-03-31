package chess.domain.piece;

import chess.domain.Team;
import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.Objects;

public abstract class Piece {

    protected static final String MOVEMENT_ERROR = "해당 기물은 그곳으로 이동할 수 없습니다.";

    private final String symbol;
    protected final Team team;

    public Piece(final Team team) {
        this.symbol = createSymbol(team);
        this.team = team;
    }

    public Direction getDirection(final Position source, final Position target) {
        int differenceRow = target.calculateRowDirection(source);
        int differenceColumn = target.calculateColumnDirection(source);
        return Direction.find(differenceRow, differenceColumn);
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isSameTeam(final Team team) {
        return this.team == team;
    }

    protected abstract String createSymbol(final Team team);

    public abstract void checkReachable(Piece targetPiece, Position source, Position target);

    public abstract boolean isBlank();

    public abstract boolean isPawn();

    public abstract boolean isKing();

    public abstract double getScore();

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return Objects.equals(symbol, piece.symbol) && team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, team);
    }

    public boolean isSameTeam(Piece targetPiece) {
        return team == targetPiece.team;
    }
}
