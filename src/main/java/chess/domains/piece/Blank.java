package chess.domains.piece;

import chess.domains.position.Position;

public class Blank extends Piece {
    public Blank(PieceColor pieceColor) {
        super(pieceColor, ".");
    }

    @Override
    public boolean canMove(Position currentPosition, Position targetPosition) {
        return false;
    }
}
