package domain.chessgame;

import domain.board.Board;
import domain.position.Position;

public class ChessGame {

    private boolean isNotEnd;
    private final Board board;

    public ChessGame() {
        this.isNotEnd = true;
        board = new Board();
        board.initChessPieces();
    }

    public void move(Position start, Position end) {
        boolean isKing = board.piece(end).isKing();
        boolean isMoved = board.move(start, end);
        if (isMoved && isKing) {
            isNotEnd = false;
        }
    }

    public boolean isNotEnd() {
        return isNotEnd;
    }

    public Board getBoard() {
        return board;
    }
}
