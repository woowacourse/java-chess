package chess.domain.piece.strategy;

import chess.domain.board.Path;
import chess.domain.position.Position;
import java.util.List;

public class RookStrategy implements PieceStrategy {

    private static final String NAME = "r";
    private static final double VALUE = 5;

    @Override
    public List<Direction> directions() {
        return Direction.straightDirection();
    }

    @Override
    public Path pathFrom(Direction direction, Position position) {
        return position.longPath(direction);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public double value() {
        return VALUE;
    }
}
