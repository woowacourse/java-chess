package domain.piece;

import domain.position.Position;

public class Pawn extends Piece {

    public Pawn(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return super.canMove(source, target) && color.canMove(source, target);
    }

    @Override
    public boolean canMove(Piece targetPiece) {
        return targetPiece.color == Color.NONE;
    }
}
