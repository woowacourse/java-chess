package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.List;

public class Knight extends Piece {

    private final List<Movement> movements = List.of(
            Movement.LEFT_LEFT_UP,
            Movement.LEFT_LEFT_DOWN,
            Movement.RIGHT_RIGHT_UP,
            Movement.RIGHT_RIGHT_DOWN,
            Movement.UP_UP_LEFT,
            Movement.UP_UP_RIGHT,
            Movement.DOWN_DOWN_LEFT,
            Movement.DOWN_DOWN_RIGHT
    );

    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    public void move(final Position position) {
        for (Movement movement : movements) {
            if (this.position.canMove(movement) && this.position.move(movement).equals(position)) {
                this.position = this.position.move(movement);
                return;
            }
        }

        throw new IllegalArgumentException("[ERROR] 나이트가 움직일 수 없는 경로입니다.");
    }

    public Position position() {
        return new Position(position.column(), position.row());
    }

    @Override
    public String name() {
        return "Knight";
    }
}
