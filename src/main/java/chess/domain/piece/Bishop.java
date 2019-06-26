package chess.domain.piece;

import chess.domain.MovingRange;
import chess.domain.board.Tile;

public class Bishop extends Piece {
    Bishop(PieceColor color, PieceType type) {
        super(color, type);
    }

    @Override
    protected MovingRange getRange(boolean isTargetEmpty, Tile current) {
        return MovingRange.BISHOP_RANGE;
    }
}
