package chess.domain.piece;

import chess.domain.position.Position;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position source, Position target, Piece targetPiece) {
        return source.isStraight(target);
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }
}
