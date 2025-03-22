package chess.domain.piece;

import static chess.domain.Movement.DOWN;
import static chess.domain.Movement.DOWN_DOWN;
import static chess.domain.Movement.LEFT_DOWN;
import static chess.domain.Movement.LEFT_UP;
import static chess.domain.Movement.RIGHT_DOWN;
import static chess.domain.Movement.RIGHT_UP;
import static chess.domain.Movement.UP;
import static chess.domain.Movement.UP_UP;

import chess.domain.Movement;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private boolean firstMove = true;

    private static final List<Movement> canMoveWhite = List.of(
            UP, RIGHT_UP, LEFT_UP, UP_UP
    );

    private static final List<Movement> canMoveBlack = List.of(
            DOWN, RIGHT_DOWN, LEFT_DOWN, DOWN_DOWN
    );

    public Pawn(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public List<Position> canMove(final Position endPosition) {
        if (color == Color.WHITE) {
            return calculateRoute(endPosition, canMoveWhite);
        }
        return calculateRoute(endPosition, canMoveBlack);
    }

    private List<Position> calculateRoute(Position endPosition, List<Movement> movements) {
        List<Position> positions = new ArrayList<>();
        for (Movement movement : movements) {
            positions.clear();
            if (movement == UP_UP && firstMove) {
                if (this.position.canMove(movement)) {
                    Position nextPosition = this.position.move(movement);
                    positions.add(nextPosition);
                    if (nextPosition.equals(endPosition)) {
                        return positions;
                    }
                }
            } else if (this.position.canMove(movement)) {
                Position nextPosition = this.position.move(movement);
                positions.add(nextPosition);
                if (nextPosition.equals(endPosition)) {
                    return positions;
                }
            }
        }

        throw new IllegalArgumentException("갈 수 없는 곳입니다.");
    }
}
