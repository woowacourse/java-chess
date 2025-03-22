package chess.piece;

import chess.Movement;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight {

    private final Position position;

    public Knight(final Position position) {
        this.position = position;
    }

    public Knight move(final Position newPosition) {
        if (!calculateCanMovePositions().contains(newPosition)) {
            throw new IllegalArgumentException("움직일 수 없는 위치입니다.");
        }
        return new Knight(newPosition);
    }

    private List<Position> calculateCanMovePositions() {
        final List<Position> positions = new ArrayList<>();
        if (position.canMove(Movement.DOWN_DOWN_RIGHT)) {
            positions.add(position.move(Movement.DOWN_DOWN_RIGHT));
        }
        if (position.canMove(Movement.DOWN_DOWN_LEFT)) {
            positions.add(position.move(Movement.DOWN_DOWN_LEFT));
        }
        if (position.canMove(Movement.UP_UP_LEFT)) {
            positions.add(position.move(Movement.UP_UP_LEFT));
        }
        if (position.canMove(Movement.UP_UP_RIGHT)) {
            positions.add(position.move(Movement.UP_UP_RIGHT));
        }
        if (position.canMove(Movement.LEFT_LEFT_DOWN)) {
            positions.add(position.move(Movement.LEFT_LEFT_DOWN));
        }
        if (position.canMove(Movement.LEFT_LEFT_UP)) {
            positions.add(position.move(Movement.LEFT_LEFT_UP));
        }
        if (position.canMove(Movement.RIGHT_RIGHT_DOWN)) {
            positions.add(position.move(Movement.RIGHT_RIGHT_DOWN));
        }
        if (position.canMove(Movement.RIGHT_RIGHT_UP)) {
            positions.add(position.move(Movement.RIGHT_RIGHT_UP));
        }
        return positions;
    }
}
