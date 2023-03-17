package chess.domain.piece;

import chess.domain.board.MoveType;
import chess.domain.position.Move;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Move move, MoveType moveType) {
        return move.isUnitStraight() || move.isUnitDiagonal();
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }
}
