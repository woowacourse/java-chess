package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.List;

public class Pawn extends Piece {
    private static final String PAWN_INITIAL = "P";

    public Pawn(Side side) {
        super(side, PAWN_INITIAL);
    }

    @Override
    protected List<Position> getRoute(Position from, Position to) {
        return Position.getRoute(from, to);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {

        if (isSideEqualTo(Side.BLACK)) {
            if (rowDifference == 1) {
                if (Math.abs(columnDifference) < 2) {
                    return true;
                }
            }
            if (rowDifference == 2) {
                return isInitPosition();
            }
        }

        if (isSideEqualTo(Side.WHITE)) {
            if (rowDifference == -1) {
                if (Math.abs(columnDifference) < 2) {
                    return true;
                }
            }
            if (rowDifference == -2) {
                return isInitPosition();
            }
        }

        return false;
    }
}
