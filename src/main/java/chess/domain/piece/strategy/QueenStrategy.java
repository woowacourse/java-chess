package chess.domain.piece.strategy;

import chess.domain.board.Path;
import chess.domain.position.Position;
import java.util.List;

public final class QueenStrategy implements PieceStrategy {

    private static final String NAME = "q";
    private static final double VALUE = 9;
    private static final String TYPE = "queen";

    @Override
    public List<Direction> directions() {
        return Direction.everyDirection();
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

    @Override
    public String type() {
        return TYPE;
    }
}
