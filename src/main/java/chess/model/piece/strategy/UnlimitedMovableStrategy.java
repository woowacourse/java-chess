package chess.model.piece.strategy;

import chess.model.Direction;
import chess.model.piece.Piece;
import java.util.List;

public class UnlimitedMovableStrategy implements MovableStrategy {

    private final List<Direction> movableDirections;

    public UnlimitedMovableStrategy(List<Direction> movableDirections) {
        this.movableDirections = movableDirections;
    }

    public boolean movable(Piece source, Piece target) {
        return !target.isAlly(source) && canMoveTo(source, target, movableDirections);
    }

    private boolean canMoveTo(Piece source, Piece target, List<Direction> movableDirections) {
        try {
            return movableDirections.contains(source.findDirectionTo(target));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
