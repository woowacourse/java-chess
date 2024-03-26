package chess.domain.piece;

import chess.domain.Position;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Piece {

    protected final Set<Direction> directions;
    protected final Color color;

    protected Piece(final Color color) {
        directions = new HashSet<>();
        this.color = color;
    }

    abstract public List<Position> findPath(final Position source, final Position target);

    abstract public String getOwnPieceTypeName();

    abstract public boolean isEmpty();

    final protected List<Position> findPathOfSingleMovePiece(final Position source, final Position target) {
        List<Position> positions = new ArrayList<>();

        Direction direction = source.calculateDirection(target);
        validateDirection(direction);

        Position currentPosition = source;
        currentPosition = currentPosition.moveTowardDirection(direction);
        positions.add(currentPosition);

        validateReachability(target, currentPosition);

        return positions;
    }

    final protected List<Position> findPathOfMultipleMovePiece(final Position source, final Position target) {
        List<Position> positions = new ArrayList<>();

        Direction direction = source.calculateDirection(target);
        validateDirection(direction);

        Position currentPosition = source;
        while (currentPosition != target) {
            currentPosition = currentPosition.moveTowardDirection(direction);
            positions.add(currentPosition);
        }

        return positions;
    }

    final protected void validateDirection(final Direction direction) {
        if (!directions.contains(direction)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물이 이동할 수 없는 방향입니다.");
        }
    }

    final protected void validateReachability(final Position target, final Position currentPosition) {
        if (!currentPosition.equals(target)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물은 해당 위치에 도달할 수 없습니다.");
        }
    }

    final public boolean canMoveInTargetDirection(final Direction targetDirection) {
        return directions.contains(targetDirection);
    }

    final public boolean isPawn() {
        return getOwnPieceTypeName().equals(PieceType.PAWN.name());
    }

    final public boolean isAlly(final Piece piece) {
        if (piece.isEmpty()) {
            return false;
        }

        return this.color == piece.color;
    }

    final public boolean isBlack() {
        return this.color == Color.BLACK;
    }
}
