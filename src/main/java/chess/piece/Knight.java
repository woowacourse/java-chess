package chess.piece;

import chess.Color;
import chess.Position;
import java.util.Map;

public class Knight extends AbstractPiece {

    public Knight(Color color, Position position) {
        super("N", color, position);
    }

    @Override
    public Knight moveTo(Position destination) {
        return new Knight(getColor(), destination);
    }

    @Override
    public boolean isMovableTo(Position destination, final Map<Position, Piece> positions) {
        if (canMoveUpLeftUp() && moveUpLeftUp().equals(destination)) {
            return true;
        }
        if (canMoveUpRightUp() && moveUpRightUp().equals(destination)) {
            return true;
        }
        if (canMoveDownLeftDown() && moveDownLeftDown().equals(destination)) {
            return true;
        }
        if (canMoveDownRightDown() && moveDownRightDown().equals(destination)) {
            return true;
        }
        if (canMoveLeftLeftUp() && moveLeftLeftUp().equals(destination)) {
            return true;
        }
        if (canMoveLeftLeftDown() && moveLeftLeftDown().equals(destination)) {
            return true;
        }
        if (canMoveRightRightUp() && moveRightRightUp().equals(destination)) {
            return true;
        }
        return canMoveRightRightDown() && moveRightRightDown().equals(destination);
    }

    private boolean canMoveUpLeftUp() {
        return getPosition().canMoveUp() && getPosition().moveUp().canMoveLeftUp();
    }

    private Position moveUpLeftUp() {
        return getPosition().moveUp().moveLeftUp();
    }

    private boolean canMoveUpRightUp() {
        return getPosition().canMoveUp() && getPosition().moveUp().canMoveRightUp();
    }

    private Position moveUpRightUp() {
        return getPosition().moveUp().moveRightUp();
    }

    private boolean canMoveDownLeftDown() {
        return getPosition().canMoveDown() && getPosition().moveDown().canMoveLeftDown();
    }

    private Position moveDownLeftDown() {
        return getPosition().moveDown().moveLeftDown();
    }

    private boolean canMoveDownRightDown() {
        return getPosition().canMoveDown() && getPosition().moveDown().canMoveRightDown();
    }

    private Position moveDownRightDown() {
        return getPosition().moveDown().moveRightDown();
    }

    private boolean canMoveLeftLeftUp() {
        return getPosition().canMoveLeft() && getPosition().moveLeft().canMoveLeftUp();
    }

    private Position moveLeftLeftUp() {
        return getPosition().moveLeft().moveLeftUp();
    }

    private boolean canMoveLeftLeftDown() {
        return getPosition().canMoveLeft() && getPosition().moveLeft().canMoveLeftDown();
    }

    private Position moveLeftLeftDown() {
        return getPosition().moveLeft().moveLeftDown();
    }

    private boolean canMoveRightRightUp() {
        return getPosition().canMoveRight() && getPosition().moveRight().canMoveRightUp();
    }

    private Position moveRightRightUp() {
        return getPosition().moveRight().moveRightUp();
    }

    private boolean canMoveRightRightDown() {
        return getPosition().canMoveRight() && getPosition().moveRightUp().canMoveRightDown();
    }

    private Position moveRightRightDown() {
        return getPosition().moveRight().moveRightDown();
    }
}
