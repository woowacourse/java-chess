package point;

import model.GameBoard;
import piece.Blank;

public class Moving {

    private final Position currentPosition;
    private final Position nextPosition;

    public Moving(Position currentPosition, Position nextPosition) {
        this.currentPosition = currentPosition;
        this.nextPosition = nextPosition;
    }

    public void move(GameBoard gameBoard) {
        if (gameBoard.findByPosition(currentPosition) instanceof Blank) {
            throw new IllegalArgumentException("기물이 없음");
        }
    }
}
