package chess.domains.piece;

import chess.domains.position.Position;

public class King extends Piece {
    public King(PieceColor pieceColor) {
        super(pieceColor, "k");
    }

    @Override
    public boolean canMove(Position currentPosition, Position targetPosition) {
        return currentPosition.isNextTo(targetPosition);
    }
}
