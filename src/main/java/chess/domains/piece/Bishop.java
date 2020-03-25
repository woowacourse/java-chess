package chess.domains.piece;

import chess.domains.position.Position;

public class Bishop extends Piece {
    public Bishop(PieceColor pieceColor) {
        super(pieceColor, "b");
    }

    @Override
    public boolean canMove(Position currentPosition, Position targetPosition) {
        return currentPosition.isInCrossLine(targetPosition);
    }
}
