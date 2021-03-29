package chess.domain.piece.strategy;

import chess.domain.board.Path;
import chess.domain.position.Position;
import java.util.List;

public final class BlackPawnStrategy implements PieceStrategy {

    private static final String NAME = "p";
    private static final double ORIGINAL_VALUE = 1;
    private static final double DUPLICATED_VALUE = 0.5;

    @Override
    public List<Direction> directions() {
        return Direction.blackPawnDirection();
    }

    @Override
    public Path pathFrom(Direction direction, Position position) {
        return position.shortPath(direction);
    }

    @Override
    public boolean isPawn() {
        return true;
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
        return ORIGINAL_VALUE;
    }
}
