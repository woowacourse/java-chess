package chess.model.piece;

import chess.model.Position;

public class Knight extends Piece {

    public Knight(PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return false;
    }
}
