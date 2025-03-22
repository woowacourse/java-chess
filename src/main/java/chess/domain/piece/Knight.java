package chess.domain.piece;

import java.util.List;
import java.util.Map;

public class Knight extends Piece {

    public Knight(PieceType pieceType, Position position, Color color) {
        super(pieceType, position, color);
    }

    @Override
    protected boolean isMoveablePosition(Position destinationPosition) {
        return true;
    }

    @Override
    protected boolean isMoveablePath(Position destinationPosition, List<Piece> allPiecesExceptMovingPiece) {
        if (destinationPosition.equals(position.moveUp().moveRightUp())) {
            Position finalTestMovingPosition = position.moveUp().moveRightUp();
            return allPiecesExceptMovingPiece.stream()
                .noneMatch(
                    piece -> piece.isExist(finalTestMovingPosition)
                        && piece.getColor() == this.color);
        }
        if (destinationPosition.equals(position.moveUp().moveLeftUp())) {
            Position finalTestMovingPosition = position.moveUp().moveLeftUp();
            return allPiecesExceptMovingPiece.stream()
                .noneMatch(
                    piece -> piece.isExist(finalTestMovingPosition)
                        && piece.getColor() == this.color);
        }
        if (destinationPosition.equals(position.moveDown().moveRightDown())) {
            Position finalTestMovingPosition = position.moveDown().moveRightDown();
            return allPiecesExceptMovingPiece.stream()
                .noneMatch(
                    piece -> piece.isExist(finalTestMovingPosition)
                        && piece.getColor() == this.color);
        }
        if (destinationPosition.equals(position.moveDown().moveLeftDown())) {
            Position finalTestMovingPosition = position.moveDown().moveLeftDown();
            return allPiecesExceptMovingPiece.stream()
                .noneMatch(
                    piece -> piece.isExist(finalTestMovingPosition)
                        && piece.getColor() == this.color);
        }
        if (destinationPosition.equals(position.moveLeft().moveLeftUp())) {
            Position finalTestMovingPosition = position.moveLeft().moveLeftUp();
            return allPiecesExceptMovingPiece.stream()
                .noneMatch(
                    piece -> piece.isExist(finalTestMovingPosition)
                        && piece.getColor() == this.color);
        }
        if (destinationPosition.equals(position.moveLeft().moveLeftDown())) {
            Position finalTestMovingPosition = position.moveLeft().moveLeftDown();
            return allPiecesExceptMovingPiece.stream()
                .noneMatch(
                    piece -> piece.isExist(finalTestMovingPosition)
                        && piece.getColor() == this.color);
        }
        if (destinationPosition.equals(position.moveRight().moveRightUp())) {
            Position finalTestMovingPosition = position.moveRight().moveRightUp();
            return allPiecesExceptMovingPiece.stream()
                .noneMatch(
                    piece -> piece.isExist(finalTestMovingPosition)
                        && piece.getColor() == this.color);
        }
        if (destinationPosition.equals(position.moveRight().moveRightDown())) {
            Position finalTestMovingPosition = position.moveRight().moveRightDown();
            return allPiecesExceptMovingPiece.stream()
                .noneMatch(
                    piece -> piece.isExist(finalTestMovingPosition)
                        && piece.getColor() == this.color);
        }
        return false;
    }
}
