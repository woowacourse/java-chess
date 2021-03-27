package chess.domain.piece.strategy;

import chess.domain.board.Path;
import chess.domain.position.Position;
import java.util.List;

public class RookStrategy implements PieceStrategy {

    @Override
    public List<Direction> directions() {
        return Direction.straightDirection();
    }

    @Override
    public Path pathFrom(Direction direction, Position position) {
        return
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
    public double score() {
        return 0;
    }
}
