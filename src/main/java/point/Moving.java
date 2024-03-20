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

    public void move(GameBoard gameBoard) { // TODO 메서드를 gameBoard 안으로 이동
        Square currentSquare = gameBoard.findByPosition(currentPosition);
        if (currentSquare.isBlank()) {
            throw new IllegalArgumentException("기물이 없음");
        }
        Square nextSquare = gameBoard.findByPosition(nextPosition);
        nextSquare.setPiece(currentSquare.getPiece());
        currentSquare.makeBlank();

    }
}
