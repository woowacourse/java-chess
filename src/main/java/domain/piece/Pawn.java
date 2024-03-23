package domain.piece;

import domain.board.Position;

import java.util.Map;

import static domain.piece.PieceColor.BLACK;
import static domain.piece.PieceColor.WHITE;
import static domain.piece.PawnMovementDirection.calculateDirection;
import static domain.board.Rank.SEVEN;
import static domain.board.Rank.TWO;

public class Pawn extends Piece {

    public Pawn(final PieceColor color) {
        super(color);
    }

    @Override
    public void checkMovable(final Position source, final Position destination, final Map<Position, Piece> piecePositions) {
        PawnMovementDirection movementDirection = calculateDirection(color, source, destination);
        if (movementDirection.isCrossStep()) {
            checkAlivePositionOfCrossStep(source.next(movementDirection), piecePositions);
            return;
        }

        if (movementDirection.isTwoStep()) {
            checkIsStartPosition(source);
        }
        checkMovePaths(source, movementDirection, piecePositions);
    }

    private void checkAlivePositionOfCrossStep(final Position alivePosition, final Map<Position, Piece> piecePositions) {
        if (!piecePositions.containsKey(alivePosition) || !checkEnemy(piecePositions.get(alivePosition))) {
            throw new IllegalArgumentException("적 기물이 존재하지 않으면 대각선으로 이동할 수 없습니다");
        }
    }

    private void checkIsStartPosition(final Position source) {
        if ((color == WHITE && source.rank() != TWO) || (color == BLACK && source.rank() != SEVEN)) {
            throw new IllegalArgumentException("시작 위치가 아니면 두 칸 이동할 수 없습니다.");
        }
    }

    private void checkMovePaths(
            final Position source,
            final PawnMovementDirection movementDirection,
            final Map<Position, Piece> piecePositions
    ) {
        Position current = source;
        int moveDistance = Math.abs(movementDirection.getRowDistance());
        for (int i = 0; i < moveDistance; i++) {
            current = current.next(movementDirection.convertOneStep());
            checkPathHasPiece(current, piecePositions);
        }
    }

    private void checkPathHasPiece(final Position path, final Map<Position, Piece> piecePositions) {
        if (piecePositions.containsKey(path)) {
            throw new IllegalArgumentException("기물이 존재하는 칸으로 이동할 수 없습니다.");
        }
    }
}
