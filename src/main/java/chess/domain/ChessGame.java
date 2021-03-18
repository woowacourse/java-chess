package chess.domain;

import chess.domain.piece.Piece;

public class ChessGame {
    private static final String WHITE = "WHITE";
    private static final String BLACK = "BLACK";

    private final Board board;
    private String currentColor = WHITE;

    public ChessGame() {
        this.board = new Board();
    }

    public void playTurn(Point source, Point target) {
        board.movePiece(source, target, currentColor);
        switchTurn();
    }

    private void switchTurn() {
        if (this.currentColor.equals(WHITE)) {
            this.currentColor = BLACK;
        }
        this.currentColor = WHITE;
    }

    public boolean isNotEnd() {
        return board.hasBothKings();
    }

    public Piece[][] getBoard() {
        return board.getBoard();
    }
}
