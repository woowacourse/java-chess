package chess.MoveStrategy;

import chess.domain.Movement;
import chess.domain.piece.Position;
import java.util.ArrayList;
import java.util.List;

public class SpecialMoveStrategy implements MoveStrategy {

    @Override
    public List<Position> canMove(final Position startPosition, final Position endPosition,
                                  final List<Movement> canMove,
                                  final boolean firstMove) {
        List<Position> positions = new ArrayList<>();
        Position currentPosition = startPosition;
        for (Movement movement : canMove) {
            positions.clear();
            while (currentPosition.canMove(movement)) {
                Position nextPosition = currentPosition.move(movement);
                positions.add(nextPosition);
                if (nextPosition.equals(endPosition)) {
                    return positions;
                }
                currentPosition = nextPosition;
            }
        }
        throw new IllegalArgumentException("갈 수 없는 곳입니다.");
    }
}
