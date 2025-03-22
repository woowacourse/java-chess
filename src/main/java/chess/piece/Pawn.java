package chess.piece;

import chess.Color;
import chess.Position;
import java.util.Map;

public class Pawn extends AbstractPiece {

    public Pawn(Color color, Position position) {
        super("P", color, position);
    }

    @Override
    public Piece moveTo(final Position position) {
        return new Pawn(getColor(), position);
    }

    @Override
    public boolean isMovableTo(final Position destination, final Map<Position, Piece> positions) {
        if (getColor() == Color.BLACK) {
            if (getPosition().moveUp().equals(destination)) {
                return true;
            }
            if (getPosition().moveDown().isBottom() && getPosition().moveUp().moveUp().equals(destination)) {
                return true;
            }
            if (positions.containsKey(destination) && canMoveLeftUp() && getPosition().moveLeftUp()
                    .equals(destination)) {
                return true;
            }
            if (positions.containsKey(destination) && canMoveRightUp() && getPosition().moveRightUp()
                    .equals(destination)) {
                return true;
            }
            return false;

        } else {
            if (getPosition().moveDown().equals(destination)) {
                return true;
            }
            if (getPosition().moveUp().isTop() && getPosition().moveDown().moveDown().equals(destination)) {
                return true;
            }
            if (positions.containsKey(destination) && canMoveLeftDown() && getPosition().moveLeftDown()
                    .equals(destination)) {
                return true;
            }
            if (positions.containsKey(destination) && canMoveRightDown() && getPosition().moveRightDown()
                    .equals(destination)) {
                return true;
            }
            return false;
        }
    }

    private boolean canMoveLeftDown() {
        return getPosition().canMoveLeftDown();
    }

    private boolean canMoveRightDown() {
        return getPosition().canMoveRightDown();
    }

    private boolean canMoveLeftUp() {
        return getPosition().canMoveLeftUp();
    }

    private boolean canMoveRightUp() {
        return getPosition().canMoveRightUp();
    }
}
