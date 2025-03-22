package chess.domain.piece;

import java.util.List;
import java.util.Map;

public class Rook extends Piece{

    public Rook(PieceType pieceType, Position position, Color color) {
        super(pieceType, position, color);
    }

    @Override
    protected boolean isMoveablePosition(Position destinationPosition) {
        Map.Entry<Integer, Integer> columnAndRowDistance = position.calculateColumnAndRowDistance(destinationPosition);
        int xDistance = columnAndRowDistance.getKey();
        int yDistance = columnAndRowDistance.getValue();

        return xDistance == 0 || yDistance == 0;
    }

    @Override
    protected boolean isMoveablePath(Position destinationPosition, List<Piece> allPiecesExceptMovingPiece) {
        Map.Entry<Integer, Integer> columnAndRowDistance = position.calculateColumnAndRowDistance(destinationPosition);
        int xDistance = columnAndRowDistance.getKey();
        int yDistance = columnAndRowDistance.getValue();

        List<Position> positions = allPiecesExceptMovingPiece.stream()
            .map(Piece::getPosition)
            .toList();

        if (xDistance == 0) {
            for (Position position : positions) {
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
            for (Position position : positions) {
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
