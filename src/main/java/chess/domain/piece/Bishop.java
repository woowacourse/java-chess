package chess.domain.piece;

import chess.domain.position.Position;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position source, Position target, Piece targetPiece) {
        return source.isDiagonal(target);
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
