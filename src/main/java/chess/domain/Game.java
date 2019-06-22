package chess.domain;

import java.util.List;

public class Game {
    private final Board board;
    private Piece.Color color;

    private Game(final Board board) {
        this.board = board;
        this.color = Piece.Color.WHITE;
    }

    public static Game from(Board board) {
        return new Game(board);
    }

    public boolean action(Position origin, Position target) {
        if (board.isSameColor(origin, color) && board.action(origin, target)) {
            color = changeColor();
            return true;
        }
        return false;
    }

    private Piece.Color changeColor() {
        return color == Piece.Color.WHITE ? Piece.Color.BLACK : Piece.Color.WHITE;
    }

    public String currentColor() {
        return color.getName();
    }

    public List<Square> getSquaresExceptEmpty() {
        return board.getSquaresExceptEmpty();
    }
}
