package domain.piece;

import domain.board.Position;

import java.util.Map;

import static domain.piece.CommonMovementDirection.calculateDirection;

public class King extends Piece {

    public King(final PieceColor color) {
        super(color);
    }

    @Override
    public void checkMovable(final Position source, final Position destination, final Map<Position, Piece> piecePositions) {
        CommonMovementDirection movementDirection = calculateDirection(source, destination);
        checkMoveDistance(source, destination, movementDirection);

        Position alivePosition = source.next(movementDirection);

        checkAlivePosition(alivePosition, piecePositions);
    }

    private void checkMoveDistance(final Position source, final Position destination, final CommonMovementDirection movementDirection) {
        int rowDistance = destination.rowIndex() - source.rowIndex();
        int columnDistance = destination.columnIndex() - source.columnIndex();

        if (movementDirection.getRowDistance() != rowDistance || movementDirection.getColumnDistance() != columnDistance) {
            throw new IllegalArgumentException("이동할 수 없는 거리입니다.");
        }
    }

    private void checkAlivePosition(final Position alivePosition, final Map<Position, Piece> piecePositions) {
        if (piecePositions.containsKey(alivePosition) && !checkEnemy(piecePositions.get(alivePosition))) {
            throw new IllegalArgumentException("아군 기물이 위치한 칸으로는 이동할 수 없습니다.");
        }
    }
}
