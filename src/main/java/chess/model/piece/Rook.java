package chess.model.piece;

import static chess.model.Direction.DOWN;
import static chess.model.Direction.LEFT;
import static chess.model.Direction.RIGHT;
import static chess.model.Direction.UP;

import chess.model.Direction;
import chess.model.Position;
import chess.model.material.Color;
import chess.model.material.Type;

public class Rook extends Piece {

    public Rook(Type type, Color color) {
        super(type, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        Direction direction = Direction.findDirection(source, target);
        return direction == UP || direction == DOWN || direction == LEFT || direction == RIGHT;
    }
}
