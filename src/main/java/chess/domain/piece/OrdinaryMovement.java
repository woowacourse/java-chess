package chess.domain.piece;

import java.util.List;

import chess.domain.board.Position;

public abstract class OrdinaryMovement implements MoveStrategy {

    @Override
    public abstract List<Position> findMovePath(Position source, Position target);

    @Override
    public List<Position> findKillPath(Position source, Position target) {
        return findMovePath(source, target);
    }
}
