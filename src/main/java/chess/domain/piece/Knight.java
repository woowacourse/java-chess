package chess.domain.piece;

import chess.domain.MovingRange;
import chess.domain.board.Tile;

public class Knight extends Piece {
    Knight(PieceColor color) {
        super(color);
    }

    @Override
    protected MovingRange getRange(boolean isTargetEmpty, Tile current) {
        return MovingRange.KNIGHT_RANGE;
    }

    @Override
    public double getScore() {
        return 2.5;
    }

    @Override
    public String getType() {
        return PieceGenerator.KNIGHT.toString();
    }
}
