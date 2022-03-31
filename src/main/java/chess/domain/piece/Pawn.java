package chess.domain.piece;

import chess.domain.board.Direction;
import java.util.List;

public abstract class Pawn extends AbstractPawnPiece {

    private static final double POINT = 1.0;

    Pawn(Color color, List<Direction> directions) {
        super(color, directions);
    }

    public static Pawn of(Color color) {
        if (color == Color.BLACK) {
            return new BlackPawn(color);
        }
        if (color == Color.WHITE) {
            return new WhitePawn(color);
        }
        throw new IllegalArgumentException("error");
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
