package chess.model.piece;

import chess.model.Position;

public class Queen extends Piece {

    public Queen(PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return false;
    }
}
