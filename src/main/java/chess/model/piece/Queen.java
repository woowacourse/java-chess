package chess.model.piece;

import static chess.model.Direction.DOWN;
import static chess.model.Direction.DOWN_LEFT;
import static chess.model.Direction.DOWN_RIGHT;
import static chess.model.Direction.LEFT;
import static chess.model.Direction.RIGHT;
import static chess.model.Direction.UP;
import static chess.model.Direction.UP_LEFT;
import static chess.model.Direction.UP_RIGHT;

import chess.model.Direction;
import chess.model.Position;
import chess.model.material.Color;
import chess.model.material.Type;

public class Queen extends Piece {

    public Queen(Type type, Color color) {
        super(type, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        Direction direction = Direction.findDirection(source, target);
        if (direction == UP_LEFT || direction == DOWN_LEFT || direction == UP_RIGHT || direction == DOWN_RIGHT) {
            return true;
        }
        return direction == UP || direction == DOWN || direction == LEFT || direction == RIGHT;
    }
}
