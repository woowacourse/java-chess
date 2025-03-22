package chess.MoveStrategy;

import static chess.domain.Movement.UP_UP;

import chess.domain.Movement;
import chess.domain.piece.Position;
import java.util.ArrayList;
import java.util.List;

public class PawnMoveStrategy implements MoveStrategy {

    @Override
    public List<Position> canMove(final Position startPosition, final Position endPosition,
                                  final List<Movement> canMove,
                                  final boolean firstMove) {

        List<Position> positions = new ArrayList<>();
        for (Movement movement : canMove) {
            positions.clear();
            if (movement == UP_UP && firstMove) {
                if (startPosition.canMove(movement)) {
                    Position nextPosition = startPosition.move(movement);
                    positions.add(nextPosition);
                    if (nextPosition.equals(endPosition)) {
                        return positions;
                    }
                }
            } else if (startPosition.canMove(movement)) {
                Position nextPosition = startPosition.move(movement);
                positions.add(nextPosition);
                if (nextPosition.equals(endPosition)) {
                    return positions;
                }
            }
        }

        throw new IllegalArgumentException("갈 수 없는 곳입니다.");
    }
}
