package chess.domain.piece;

import chess.domain.position.Position;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position source, Position target, Piece targetPiece) {
        return source.isStraight(target) || source.isDiagonal(target);
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
}
