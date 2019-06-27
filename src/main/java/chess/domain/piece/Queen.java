package chess.domain.piece;

import chess.domain.MovingRange;
import chess.domain.board.Tile;

public class Queen extends Piece {
    Queen(PieceColor color) {
        super(color);
    }

    @Override
    protected MovingRange getRange(boolean isTargetEmpty, Tile current) {
        return MovingRange.QUEEN_RANGE;
    }

    @Override
    public double getScore() {
        return 9;
    }

    @Override
    public String getType() {
        return PieceGenerator.QUEEN.toString();
    }
}
