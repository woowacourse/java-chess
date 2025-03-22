package chess.domain.piece;

import java.util.List;
import java.util.Map;

public class Bishop extends Piece {

    public Bishop(PieceType pieceType, Position position, Color color) {
        super(pieceType, position, color);
    }

    @Override
    protected boolean isMoveablePosition(Position destinationPosition) {
        Map.Entry<Integer, Integer> columAndRowDistance = this.position.calculateColumnAndRowDistance(destinationPosition);
        int xDistance = Math.abs(columAndRowDistance.getKey());
        int yDistance = Math.abs(columAndRowDistance.getValue());
        return xDistance == yDistance;
    }

    @Override
    protected boolean isMoveablePath(Position destinationPosition, List<Piece> allPiecesExceptMovingPiece) {
        Map.Entry<Integer, Integer> columAndRowDistance = this.position.calculateColumnAndRowDistance(destinationPosition);
        int xDistance = columAndRowDistance.getKey();
        int yDistance = columAndRowDistance.getValue();

        List<Position> otherPiecesPositions = allPiecesExceptMovingPiece.stream()
            .map(Piece::getPosition)
            .toList();

        Position testMovingPosition = this.position;
        if (xDistance > 0) {
            if (yDistance > 0) {
                for (int i = 0; i < Math.abs(xDistance); i++) {
                    testMovingPosition = testMovingPosition.moveRightUp();
                    if (otherPiecesPositions.contains(testMovingPosition)) {
                        Position finalTestMovingPosition = testMovingPosition;
                        return allPiecesExceptMovingPiece.stream()
                            .noneMatch(
                                piece -> piece.isExist(finalTestMovingPosition)
                                    && piece.getColor() == this.color);
                    }
                }
            }
            if (yDistance < 0) {
                for (int i = 0; i < Math.abs(xDistance); i++) {
                    testMovingPosition = testMovingPosition.moveRightDown();
                    if (otherPiecesPositions.contains(testMovingPosition)) {
                        Position finalTestMovingPosition = testMovingPosition;
                        return allPiecesExceptMovingPiece.stream()
                            .noneMatch(
                                piece -> piece.isExist(finalTestMovingPosition)
                                    && piece.getColor() == this.color);
                    }
                }
            }
        }
        if (xDistance < 0) {
            if (yDistance > 0) {
                for (int i = 0; i < Math.abs(xDistance); i++) {
                    testMovingPosition = testMovingPosition.moveLeftUp();
                    if (otherPiecesPositions.contains(testMovingPosition)) {
                        Position finalTestMovingPosition = testMovingPosition;
                        return allPiecesExceptMovingPiece.stream()
                            .noneMatch(
                                piece -> piece.isExist(finalTestMovingPosition)
                                    && piece.getColor() == this.color);
                    }
                }
            }
            if (yDistance < 0) {
                for (int i = 0; i < Math.abs(xDistance); i++) {
                    testMovingPosition = testMovingPosition.moveLeftDown();
                    if (otherPiecesPositions.contains(testMovingPosition)) {
                        Position finalTestMovingPosition = testMovingPosition;
                        return allPiecesExceptMovingPiece.stream()
                            .noneMatch(
                                piece -> piece.isExist(finalTestMovingPosition)
                                    && piece.getColor() == this.color);
                    }
                }
            }
        }
        return true;
    }
}
