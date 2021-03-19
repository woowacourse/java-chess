package chess.domain;

import chess.domain.piece.Piece;

public class ChessGame {
    public static final String WHITE = "WHITE";
    public static final String BLACK = "BLACK";

    private final Board board;
    private String currentColor = WHITE;

    public ChessGame() {
        this.board = new Board();
    }

    public void playTurn(Point source, Point target) {
        board.movePiece(source, target, currentColor);
        this.currentColor = switchTurn();
    }

    private String switchTurn() {
        if (this.currentColor.equals(WHITE)) {
            return BLACK;
        }
        return WHITE;
    }

    public boolean isNotEnd() {
        return board.hasBothKings();
    }

    public Piece[][] getBoard() {
        return board.getBoard();
    }
}
