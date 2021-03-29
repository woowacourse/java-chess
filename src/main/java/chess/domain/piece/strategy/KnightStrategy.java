package chess.domain.piece.strategy;

import chess.domain.board.Path;
import chess.domain.position.Position;
import java.util.List;

public final class KnightStrategy implements PieceStrategy {

    private static final String NAME = "n";
    private static final double VALUE = 3;

    @Override
    public List<Direction> directions() {
        return Direction.knightDirection();
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
