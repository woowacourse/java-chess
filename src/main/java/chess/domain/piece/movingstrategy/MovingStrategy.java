package chess.domain.piece.movingstrategy;

import chess.domain.piece.PieceColor;
import chess.domain.position.Position;
import chess.domain.position.directionstrategy.DirectionStrategy;
import java.util.Set;

public abstract class MovingStrategy {

    private final Set<DirectionStrategy> directionStrategies;

    MovingStrategy() {
        this.directionStrategies = Set.of();
    }

    MovingStrategy(Set<DirectionStrategy> directionStrategies) {
        this.directionStrategies = directionStrategies;
    }

    public boolean isAbleToMove(Position from, Position to, PieceColor pieceColor) {
        if (from.equals(to)) {
            return false;
        }

        return directionStrategies.stream()
                .anyMatch(directionStrategy -> directionStrategy.checkDirection(from, to));
    }

    public boolean isAbleToAttack(Position from, Position to, PieceColor pieceColor) {
        return isAbleToMove(from, to, pieceColor);
    }
}
