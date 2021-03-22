package domain.chessgame;

import domain.board.Board;
import domain.position.Position;

public class ChessGame {

    private final boolean isNotEnd;
    private final Board board;

    public ChessGame() {
        this.isNotEnd = true;
        board = new Board();
        board.initChessPieces();
    }

    public void move(Position start, Position end) {
        board.move(start, end);
    }

    public boolean isNotEnd() {
        return isNotEnd;
    }

    public Board getBoard() {
        return board;
    }
}
