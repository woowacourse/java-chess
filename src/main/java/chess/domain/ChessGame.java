package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.kind.Piece;

import java.util.Map;

import static chess.domain.piece.Color.*;

public class ChessGame {
    private final Board board;
    private Color currentColor = WHITE;

    public ChessGame() {
        this.board = new Board();
    }

    public void playTurn(Point source, Point target) {
        board.movePiece(source, target, currentColor);
        this.currentColor = switchTurn();
    }

    private Color switchTurn() {
        if (this.currentColor.equals(WHITE)) {
            return BLACK;
        }
        return WHITE;
    }

    public boolean isNotEnd() {
        return board.hasBothKings();
    }

    public double calculateScore() {
        return board.addScore(currentColor);
    }

    public Map<Point, Piece> getBoard() {
        return board.getBoard();
    }
}
