package chess.domains.piece;

import chess.domains.position.Position;

public class Queen extends Piece {
    public Queen(PieceColor pieceColor) {
        super(pieceColor, "q");
    }

    @Override
    public boolean canMove(Position currentPosition, Position targetPosition) {
        return currentPosition.isInLine(targetPosition) || currentPosition.isInCrossLine(targetPosition);
    }
}
