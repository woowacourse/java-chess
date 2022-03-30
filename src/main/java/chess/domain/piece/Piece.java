package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.direction.Direction;
import chess.domain.piece.movestrategy.MoveStrategy;
import java.util.Objects;

public abstract class Piece {

    protected final Symbol symbol;
    protected final Team team;

    protected Piece(final Symbol symbol, final Team team) {
        this.symbol = symbol;
        this.team = team;
    }

    public boolean isMovable(Board board, Coordinate from, Coordinate to) {
        Piece toPiece = board.findPiece(to);
        if (isSameTeam(toPiece.team) || hasNotDirection(Direction.of(from, to))) {
            return false;
        }
        return moveStrategy().isMovable(board, from, to);
    }

    public abstract boolean hasNotDirection(Direction direction);

    public abstract MoveStrategy moveStrategy();

    public boolean isSameTeam(Team team) {
        return this.team.isSame(team);
    }

    public boolean isKing() {
        return symbol.isKing();
    }

    public boolean isPawn() {
        return symbol.isPawn();
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isExist() {
        return true;
    }

    public boolean isBlack() {
        return team.isBlack();
    }

    public boolean isWhite() {
        return team.isWhite();
    }

    public String getSymbol() {
        if (team.isBlack()) {
            return symbol.getBlack();
        }
        return symbol.getWhite();
    }

    public Team getTeam() {
        return team;
    }

    public double getScore() {
        return symbol.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return symbol == piece.symbol && team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, team);
    }
}
