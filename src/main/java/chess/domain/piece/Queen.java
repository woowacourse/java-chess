package chess.domain.piece;

import chess.domain.position.Move;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Move move, Piece targetPiece) {
        return move.isStraight() || move.isDiagonal();
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
}
