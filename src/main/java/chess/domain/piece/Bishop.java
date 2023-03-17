package chess.domain.piece;

import chess.domain.board.MoveType;
import chess.domain.position.Move;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Move move, MoveType moveType) {
        return move.isDiagonal();
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
