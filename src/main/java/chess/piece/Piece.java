package chess.piece;

import chess.position.Position;
import chess.position.UnitDirection;
import java.util.Set;
import java.util.stream.Stream;

public abstract class Piece {

    private final Color color;
    private final int maxUnitMove;
    private final Set<UnitDirection> unitDirections;

    protected Piece(Color color, int maxUnitMove, Set<UnitDirection> unitDirections) {
        this.color = color;
        this.maxUnitMove = maxUnitMove;
        this.unitDirections = unitDirections;
    }

    public boolean isMovable(Position source, Position destination) {
        UnitDirection direction = source.unitDirectionToward(destination);
        return unitDirections.contains(direction) &&
                isReachable(source, destination, direction);
    }

    private boolean isReachable(Position source, Position destination, UnitDirection unitDirection) {
        int distance = (int) Stream.iterate(source,
                        position -> position.isNotEquals(destination),
                        unitDirection::nextPosition)
                .count();
        return distance <= maxUnitMove;
    }

    public boolean canAttack(Position source, Position destination) {
        return isMovable(source, destination);
    }

    public boolean isNotMovable(Position source, Position destination) {
        return !isMovable(source, destination);
    }

    public boolean canNotCapture(Position source, Position destination) {
        return !canAttack(source, destination);
    }

    public boolean isInitPawn() {
        return false;
    }

    public boolean hasSameColorWith(Piece piece) {
        return color == piece.color;
    }

    public boolean hasDifferentColorWith(Piece piece) {
        return !hasSameColorWith(piece);
    }

    public boolean hasColorOf(Color color) {
        return this.color == color;
    }

    public boolean hasOpponentColorOf(Color currentTurnColor) {
        return color != currentTurnColor;
    }
}
