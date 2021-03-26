package chess.domain.piece;

import chess.domain.game.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMovementException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public abstract class GamePiece implements Piece {

    private final Side side;
    private final String initial;

    public GamePiece(Side side, String initial) {
        this.side = side;
        this.initial = initial;
    }

    @Override
    public final List<Position> route(Position from, Position to, Piece targetPiece, Side side) {
        checkMyPiece(side);
        checkNotMyPosition(from, to);
        checkMyPieceNotExistTargetPosition(targetPiece, side);
        checkMovable(from, to, targetPiece);
        return getRoute(from, to);
    }

    private void checkMyPiece(Side side) {
        if (!isSideEqualTo(side)) {
            throw new InvalidMovementException("자신의 기물만 움직일 수 있습니다.");
        }
    }

    private void checkNotMyPosition(Position from, Position to) {
        if (Objects.equals(from, to)) {
            throw new InvalidMovementException("자기 자신의 위치로는 이동할 수 없습니다.");
        }
    }

    private void checkMyPieceNotExistTargetPosition(Piece targetPiece, Side side) {
        if (targetPiece.isSideEqualTo(side)) {
            throw new InvalidMovementException("이동하려는 위치에 자신의 기물이 존재합니다.");
        }
    }

    private void checkMovable(Position from, Position to, Piece targetPiece) {
        int rowDifference = Position.differenceOfRow(from, to);
        int columnDifference = Position.differenceOfColumn(from, to);

        if (!movable(rowDifference, columnDifference, targetPiece)) {
            throw new InvalidMovementException("해당 기물의 이동 룰에 어긋납니다.");
        }
    }

    protected abstract boolean movable(int rowDifference, int columnDifference, Piece targetPiece);

    protected abstract List<Position> getRoute(Position from, Position to);

    @Override
    public final boolean isSideEqualTo(Side side) {
        return this.side == side;
    }

    @Override
    public final boolean isBlank() {
        return false;
    }

    @Override
    public final String getInitial() {
        if (isSideEqualTo(Side.WHITE)) {
            return initial.toLowerCase(Locale.ROOT);
        }
        if (isSideEqualTo(Side.BLACK)) {
            return initial.toUpperCase(Locale.ROOT);
        }
        return initial;
    }
}
