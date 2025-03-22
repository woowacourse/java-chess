package chess.domain.piece;

import static chess.domain.Movement.LEFT_DOWN;
import static chess.domain.Movement.LEFT_UP;
import static chess.domain.Movement.RIGHT_DOWN;
import static chess.domain.Movement.RIGHT_UP;

import chess.domain.Movement;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    private final static List<Movement> canMove = List.of(
            RIGHT_UP, RIGHT_DOWN, LEFT_DOWN, LEFT_UP
    );

    public Bishop(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public List<Position> canMove(final Position endPosition) {
        List<Position> positions = new ArrayList<>();
        Position currentPosition = this.position;
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

