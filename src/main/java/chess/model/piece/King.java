package chess.model.piece;

import chess.model.Position;

public class King extends Piece {

    public King(PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return false;
    }
}
