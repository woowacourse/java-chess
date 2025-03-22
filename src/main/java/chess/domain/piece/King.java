package chess.domain.piece;

import static chess.domain.Movement.DOWN;
import static chess.domain.Movement.LEFT;
import static chess.domain.Movement.RIGHT;
import static chess.domain.Movement.UP;

import chess.domain.Movement;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private final static List<Movement> canMove = List.of(
            RIGHT, LEFT, UP, DOWN
    );

    public King(final Color color, final Position position) {
        super(color, position);
    }


    @Override
    public List<Position> canMove(final Position endPosition) {
        List<Position> positions = new ArrayList<>();
        for (Movement movement : canMove) {
            positions.clear();
            if (this.position.canMove(movement)) {
                Position nextPosition = this.position.move(movement);
                positions.add(nextPosition);
                if (nextPosition.equals(endPosition)) {
                    return positions;
                }
            }
        }
        throw new IllegalArgumentException("갈 수 없는 위치입니다.");
    }
}
