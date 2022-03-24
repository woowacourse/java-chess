package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.List;

public final class Queen extends Piece {

    private final AbstractStraightMovePattern pattern = new AbstractStraightMovePattern() {
        @Override
        public List<Direction> getDirections() {
            return Direction.getQueenDirections();
        }
    };

    public Queen(Color color) {
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
}
