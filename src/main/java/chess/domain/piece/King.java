package chess.domain.piece;

import java.util.List;
import java.util.Map;

public class King extends Piece {

    public King(PieceType pieceType, Position position, Color color) {
        super(pieceType, position, color);
    }

    @Override
    protected boolean isMoveablePosition(Position destinationPosition) {
        if (this.position.moveRight().equals(destinationPosition)) {
            return true;
        }
        if (this.position.moveLeft().equals(destinationPosition)) {
            return true;
        }
        if (this.position.moveRight().equals(destinationPosition)) {
            return true;
        }
        if (this.position.moveDown().equals(destinationPosition)) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean isMoveablePath(Position destinationPosition, List<Piece> allPiecesExceptMovingPiece) {
        List<Position> allPositions = allPiecesExceptMovingPiece.stream()
            .map(Piece::getPosition)
            .toList();

        if (allPositions.contains(this.position.moveRight())) {
            Position finalTestMovingPosition = this.position.moveRight();
            return allPiecesExceptMovingPiece.stream()
                .noneMatch(piece -> piece.isExist(finalTestMovingPosition)
                        && piece.getColor() == this.color);
        }
        if (allPositions.contains(this.position.moveLeft())) {
            Position finalTestMovingPosition = this.position.moveLeft();
            return allPiecesExceptMovingPiece.stream()
                .noneMatch(piece -> piece.isExist(finalTestMovingPosition)
                    && piece.getColor() == this.color);
        }
        if (allPositions.contains(this.position.moveDown())) {
            Position finalTestMovingPosition = this.position.moveDown();
            return allPiecesExceptMovingPiece.stream()
                .noneMatch(piece -> piece.isExist(finalTestMovingPosition)
                    && piece.getColor() == this.color);
        }
        if (allPositions.contains(this.position.moveUp())) {
            Position finalTestMovingPosition = this.position.moveUp();
            return allPiecesExceptMovingPiece.stream()
                .noneMatch(piece -> piece.isExist(finalTestMovingPosition)
                    && piece.getColor() == this.color);
        }
        return true;
    }
}
