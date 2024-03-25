package chess.model.piece;

import static chess.model.Direction.DOWN;
import static chess.model.Direction.LEFT;
import static chess.model.Direction.RIGHT;
import static chess.model.Direction.UP;

import chess.model.Direction;
import chess.model.Position;

public class Rook extends Piece {

    public Rook(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        Direction direction = Direction.findDirection(source, target);
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
