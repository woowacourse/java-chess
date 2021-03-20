package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMovementException;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public abstract class Piece {
    private final Side side;
    private final String initial;
    private boolean initPosition = true;

    public Piece(Side side, String initial) {
        this.side = side;
        this.initial = initial;
    }

    public List<Position> route(Position from, Position to) {
        if (Objects.equals(from, to)) {
            throw new InvalidMovementException("자기 자신의 위치로는 이동할 수 없습니다.");
        }

        int rowDifference = Position.differenceOfRow(from, to);
        int columnDifference = Position.differenceOfColumn(from, to);

        if (movable(rowDifference, columnDifference)) {
            return getRoute(from, to);
        }

        throw new InvalidMovementException("해당 기물의 이동 룰에 어긋납니다.");
    }

    protected abstract boolean movable(int rowDifference, int columnDifference);

    protected abstract List<Position> getRoute(Position from, Position to);

    public abstract boolean isBlank();

    public abstract boolean isPawn();

    public abstract boolean isKing();

    public abstract double score();

    public void moved() {
        initPosition = false;
    }

    protected boolean isInitPosition() {
        return initPosition;
    }

    public boolean isSideEqualTo(Side side) {
        return this.side == side;
    }

    public String getInitial() {
        if (side == Side.WHITE) {
            return initial.toLowerCase(Locale.ROOT);
        }
        if (side == Side.BLACK) {
            return initial.toUpperCase(Locale.ROOT);
        }
        return initial;
    }

    public abstract boolean diagonal(Position from, Position to);

    public abstract boolean forward(Position from, Position to);
}
