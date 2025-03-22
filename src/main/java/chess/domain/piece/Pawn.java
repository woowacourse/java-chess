package chess.domain.piece;

import java.util.List;

public class Pawn extends Piece {

    public Pawn(PieceType pieceType, Position position, Color color) {
        super(pieceType, position, color);
    }

    @Override
    protected boolean isMoveablePosition(Position destinationPosition) {
        if (this.color == Color.BLACK) {
            if (this.position.hasSameRow(Row.SEVEN)) return position.moveDown(2).equals(destinationPosition);
            if (this.position.moveUp().equals(destinationPosition)) return true;
            if (this.position.moveRight().equals(destinationPosition)) return true;
            if (this.position.moveLeft().equals(destinationPosition)) return true;
            if (this.position.moveRightUp().equals(destinationPosition)
                || this.position.moveLeftUp().equals(destinationPosition)) return true;
            return false;
        }
        if (this.position.hasSameRow(Row.TWO)) return position.moveUp(2).equals(destinationPosition);
        if (this.position.moveDown().equals(destinationPosition)) return true;
        if (this.position.moveRight().equals(destinationPosition)) return true;
        if (this.position.moveLeft().equals(destinationPosition)) return true;
        if (this.position.moveRightDown().equals(destinationPosition)
            || this.position.moveLeftDown().equals(destinationPosition)) return true;
        return false;
    }

    @Override
    protected boolean isMoveablePath(Position destinationPosition, List<Piece> allPiecesExceptMovingPiece) {
        if (this.color == Color.BLACK) {
            if (this.position.hasSameRow(Row.SEVEN)) return position.moveDown(2).equals(destinationPosition);
            if (this.position.moveUp().equals(destinationPosition)) return true;
            if (this.position.moveRight().equals(destinationPosition)) return true;
            if (this.position.moveLeft().equals(destinationPosition)) return true;
            if (this.position.moveRightUp().equals(destinationPosition)) {
                return allPiecesExceptMovingPiece.stream()
                    .noneMatch(piece -> piece.isExist(destinationPosition)
                        && piece.getColor() != this.color);
            }
            if (this.position.moveLeftUp().equals(destinationPosition)) {
                return allPiecesExceptMovingPiece.stream()
                    .noneMatch(piece -> piece.isExist(destinationPosition)
                        && piece.getColor() != this.color);
            }
            return false;
        }
        if (this.position.hasSameRow(Row.TWO)) return position.moveUp(2).equals(destinationPosition);
        if (this.position.moveDown().equals(destinationPosition)) return true;
        if (this.position.moveRight().equals(destinationPosition)) return true;
        if (this.position.moveLeft().equals(destinationPosition)) return true;
        if (this.position.moveRightDown().equals(destinationPosition)) {
            return allPiecesExceptMovingPiece.stream()
                .noneMatch(piece -> piece.isExist(destinationPosition)
                    && piece.getColor() != this.color);
        }
        if (this.position.moveLeftDown().equals(destinationPosition)) {
            return allPiecesExceptMovingPiece.stream()
                .noneMatch(piece -> piece.isExist(destinationPosition)
                    && piece.getColor() != this.color);
        }
        return false;
    }
}
