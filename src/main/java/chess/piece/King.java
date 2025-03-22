package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.List;

public class King extends Piece {

    private final List<Movement> movements = List.of(
            Movement.UP, Movement.DOWN, Movement.LEFT,
            Movement.RIGHT, Movement.LEFT_UP, Movement.RIGHT_UP,
            Movement.LEFT_DOWN, Movement.RIGHT_DOWN
    );

    public King(Color color, Position position) {
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

        throw new IllegalArgumentException("[ERROR] 움직일 수 없음.");
    }

    public Position position() {
        return new Position(position.column(), position.row());
    }

    @Override
    public String name() {
        return "King";
    }
}
