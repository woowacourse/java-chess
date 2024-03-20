package point;

import model.GameBoard;
import model.Square;

public class Moving {

    private final Position currentPosition;
    private final Position nextPosition;

    public Moving(Position currentPosition, Position nextPosition) {
        this.currentPosition = currentPosition;
        this.nextPosition = nextPosition;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Position getNextPosition() {
        return nextPosition;
    }
}
