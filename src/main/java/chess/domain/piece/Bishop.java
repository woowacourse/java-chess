package chess.domain.piece;

import chess.domain.MovingRange;
import chess.domain.board.Tile;

public class Bishop extends Piece {
    Bishop(PieceColor color) {
        super(color);
    }

    @Override
    protected MovingRange getRange(boolean isTargetEmpty, Tile current) {
        return MovingRange.BISHOP_RANGE;
    }

    @Override
    public double getScore() {
        return 3;
    }

    @Override
    public String getType() {
        return PieceGenerator.BISHOP.toString();
    }
}
