package chess.piece;

import chess.board.Direction;
import chess.board.Position;
import java.util.List;

public class Knight extends Piece {

    private static final int MAX_UNIT_MOVE = 1;

    public Knight(Color color) {
        super(PieceType.KNIGHT, color, List.of(Direction.KNIGHT));
    }

    @Override
    protected boolean isReachable(Position source, Position destination, Direction direction) {
        return true;
    }

    @Override
    protected int getMaxUnitMove() {
        return MAX_UNIT_MOVE;
    }
}
