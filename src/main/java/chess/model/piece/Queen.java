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

public class Queen extends Piece {

    public Queen(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        Direction direction = Direction.findDirection(source, target);
        if (direction == UP_LEFT || direction == DOWN_LEFT || direction == UP_RIGHT || direction == DOWN_RIGHT) {
            return true;
        }
        return direction == UP || direction == DOWN || direction == LEFT || direction == RIGHT;
    }

    @Override
    public boolean canAttack(Position source, Position target) {
        return canMove(source, target);
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
