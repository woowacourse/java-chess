package chess.domain.piece;

import static chess.domain.board.Direction.NOTHING;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.List;

public abstract class AbstractOncePiece extends Piece {

    private static final int ONCE_TIME = 1;

    AbstractOncePiece(Color color, List<Direction> directions) {
        super(color, directions);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean canMove(Position source, Position destination) {
        return findDirection(source, destination) != NOTHING;
    }

    @Override
    public Direction findDirection(Position source, Position destination) {
        return directions.stream()
                .filter(direction -> source.canMoveByTime(direction, destination, ONCE_TIME))
                .findFirst()
                .orElse(NOTHING);
    }
}
