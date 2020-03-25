package chess.domains.piece;

import chess.domains.position.Position;

public class Rook extends Piece {
    public Rook(PieceColor pieceColor) {
        super(pieceColor, "r");
    }

    @Override
    public boolean canMove(Position currentPosition, Position targetPosition) {
        return currentPosition.isInLine(targetPosition);
    }
}
