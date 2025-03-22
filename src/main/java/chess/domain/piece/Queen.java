package chess.domain.piece;

import java.util.List;
import java.util.Map;

public class Queen extends Piece {

    public Queen(PieceType pieceType, Position position, Color color) {
        super(pieceType, position, color);
    }

    @Override
    protected boolean isMoveablePosition(Position destinationPosition) {
        Map.Entry<Integer, Integer> columnAndRowDistance = position.calculateColumnAndRowDistance(destinationPosition);
        int xDistance = columnAndRowDistance.getKey();
        int yDistance = columnAndRowDistance.getValue();

        if (xDistance == 0) {
            return yDistance != 0;
        }
        if (yDistance == 0) {
            return true;
        }
        return Math.abs(xDistance) == Math.abs(yDistance);
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
        if (xDistance == 0) {
            for (Position position : otherPiecesPositions) {
                Map.Entry<Integer, Integer> integerIntegerEntry = this.position.calculateColumnAndRowDistance(position);
                int otherPieceYDistance  = integerIntegerEntry.getValue();
                if (yDistance == otherPieceYDistance) {
                    Position finalTestMovingPosition = this.position.moveUp(yDistance);
                    return allPiecesExceptMovingPiece.stream()
                        .noneMatch(
                            piece -> piece.isExist(finalTestMovingPosition)
                                && piece.getColor() == this.color);
                }
                if (yDistance > 0 && otherPieceYDistance > 0) {
                    return yDistance > otherPieceYDistance;
                }
                if (yDistance < 0 && otherPieceYDistance < 0) {
                    return yDistance < otherPieceYDistance;
                }
                return false;
            }
        }
        if (yDistance == 0) {
            for (Position position : otherPiecesPositions) {
                Map.Entry<Integer, Integer> integerIntegerEntry = this.position.calculateColumnAndRowDistance(position);
                int otherPieceXDistance  = integerIntegerEntry.getKey();
                if (xDistance == otherPieceXDistance) {
                    Position finalTestMovingPosition = this.position.moveUp(yDistance);
                    return allPiecesExceptMovingPiece.stream()
                        .noneMatch(
                            piece -> piece.isExist(finalTestMovingPosition)
                                && piece.getColor() == this.color);
                }
                if (xDistance > 0 && otherPieceXDistance > 0) {
                    return xDistance > otherPieceXDistance;
                }
                if (xDistance < 0 && otherPieceXDistance < 0) {
                    return xDistance < otherPieceXDistance;
                }
                return false;
            }
        }
        return false;
    }
}
