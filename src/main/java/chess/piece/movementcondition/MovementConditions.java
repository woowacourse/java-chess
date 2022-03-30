package chess.piece.movementcondition;

import chess.piece.Piece;
import chess.position.Position;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MovementConditions implements MovementCondition {

    private final Set<MovementCondition> conditions;

    public MovementConditions(Set<MovementCondition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public boolean isPossibleMovement(Position from, Position to, Map<Position, Piece> board) {
        return conditions.stream()
                .allMatch(condition -> condition.isPossibleMovement(from, to, board));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovementConditions that = (MovementConditions) o;
        return Objects.equals(conditions, that.conditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conditions);
    }
}
