package chess.domain;

import static chess.domain.piece.Color.*;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.kind.Piece;

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

    public Score calculateScore(Color color) {
        return board.makeScore(color);
    }

    public Map<Point, Piece> getBoard() {
        return board.getBoard();
    }
}
