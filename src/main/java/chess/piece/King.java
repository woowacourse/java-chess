package chess.piece;

import chess.Color;
import chess.Position;
import java.util.Map;

public class King extends AbstractPiece {

    public King(Color color, Position position) {
        super("K", color, position);
    }

    public King moveTo(Position destination) {
        return new King(getColor(), destination);
    }

    public boolean isMovableTo(Position destination, final Map<Position, Piece> positions) {
        if (canMoveUp(destination) || canMoveDown(destination) || canMoveLeft(destination) || canMoveRight(destination)) {
            return true;
        }
        if (canMoveLeftUp(destination) || canMoveLeftDown(destination) || canMoveRightUp(destination) || canMoveRightDown(destination)) {
            return true;
        }
        return false;
    }

    private boolean canMoveUp(Position destination) {
        return getPosition().canMoveUp() && getPosition().moveUp().equals(destination);
    }

    private boolean canMoveDown(Position destination) {
        return getPosition().canMoveDown() && getPosition().moveDown().equals(destination);
    }

    private boolean canMoveLeft(Position destination) {
        return getPosition().canMoveLeft() && getPosition().moveLeft().equals(destination);
    }

    private boolean canMoveRight(Position destination) {
        return getPosition().canMoveRight() && getPosition().moveRight().equals(destination);
    }

    private boolean canMoveLeftUp(Position destination) {
        return getPosition().canMoveLeftUp() && getPosition().moveLeftUp().equals(destination);
    }

    private boolean canMoveLeftDown(Position destination) {
        return getPosition().canMoveLeftDown() && getPosition().moveLeftDown().equals(destination);
    }

    private boolean canMoveRightUp(Position destination) {
        return getPosition().canMoveRightUp() && getPosition().moveRightUp().equals(destination);
    }

    private boolean canMoveRightDown(Position destination) {
        return getPosition().canMoveRightDown() && getPosition().moveRightDown().equals(destination);
    }
}
