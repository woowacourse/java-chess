package chess.domain.board;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.board.movePattern.AbstractStraightMovePattern;
import java.util.List;

public final class Rook extends Piece {

    private static final double POINT = 5.0;

    private final AbstractStraightMovePattern pattern = new AbstractStraightMovePattern() {
        @Override
        public List<Direction> getDirections() {
            return Direction.getRookDirections();
        }
    };

    public Rook(Color color) {
        super(color);
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
