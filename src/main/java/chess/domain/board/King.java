package chess.domain.board;

import chess.domain.board.movePattern.AbstractSingleMovePattern;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public final class King extends Piece {

    private static final double POINT = 0.0;

    private final AbstractSingleMovePattern pattern = new AbstractSingleMovePattern() {
        @Override
        public List<Direction> getDirections() {
            return Direction.getKingDirections();
        }
    };

    public King(Color color) {
        super(color, "_king");
    }

    @Override
    public boolean canMove(Position src, Position dest) {
        return pattern.canMove(src, dest);
    }

    @Override
    public Direction findDirection(Position src, Position dest) {
        return pattern.findDirection(src, dest);
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
