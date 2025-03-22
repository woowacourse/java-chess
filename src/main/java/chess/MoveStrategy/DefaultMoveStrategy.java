package chess.MoveStrategy;

import chess.domain.Movement;
import chess.domain.piece.Position;
import java.util.ArrayList;
import java.util.List;

public class DefaultMoveStrategy implements MoveStrategy {

    @Override
    public List<Position> canMove(final Position startPosition, final Position endPosition, List<Movement> canMove,
                                  final boolean firstMove) {

        List<Position> positions = new ArrayList<>();
        for (Movement movement : canMove) {
            positions.clear();
            if (startPosition.canMove(movement)) {
                Position nextPosition = startPosition.move(movement);
                positions.add(nextPosition);
                if (nextPosition.equals(endPosition)) {
                    return positions;
                }
            }
        }
        throw new IllegalArgumentException("갈 수 없는 위치입니다.");
    }
}
