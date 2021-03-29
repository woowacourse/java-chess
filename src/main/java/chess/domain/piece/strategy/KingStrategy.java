package chess.domain.piece.strategy;

import chess.domain.board.Path;
import chess.domain.position.Position;
import java.util.List;

public final class KingStrategy implements PieceStrategy {

    private static final String NAME = "k";
    private static final double VALUE = 0;

    @Override
    public List<Direction> directions() {
        return Direction.everyDirection();
    }

    @Override
    public Path pathFrom(Direction direction, Position position) {
        return position.shortPath(direction);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
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
