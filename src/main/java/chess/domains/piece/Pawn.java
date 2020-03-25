package chess.domains.piece;

import chess.domains.position.Position;

public class Pawn extends Piece {
    public Pawn(PieceColor pieceColor) {
        super(pieceColor, "p");
    }

    @Override
    public boolean canMove(Position currentPosition, Position targetPosition) {
        return false;
    }
}
