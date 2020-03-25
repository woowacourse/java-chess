package chess.domains.piece;

import chess.domains.position.Position;

public class Knight extends Piece {
    public Knight(PieceColor pieceColor) {
        super(pieceColor, "n");
    }

    @Override
    public boolean canMove(Position currentPosition, Position targetPosition) {
        return currentPosition.isInYshape(targetPosition);
    }
}
