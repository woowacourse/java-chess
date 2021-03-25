package chess.domain;

import static chess.domain.board.Board.*;
import static chess.domain.piece.Color.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Piece;

public final class ChessGame {
    private final Board board;
    private Color currentColor = WHITE;

    public ChessGame() {
        this.board = initialize(new HashMap<>());
    }

    private Board initialize(Map<Point, Piece> boardMap) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            initializeColumn(boardMap, i);
        }
        return new Board(boardMap);
    }

    private void initializeColumn(Map<Point, Piece> boardMap, int i) {
        for (int j = 0; j < BOARD_SIZE; j++) {
            boardMap.put(Point.valueOf(i, j), PieceType.findPiece(i, j));
        }
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

    public boolean isEnd() {
        return !board.hasBothKings();
    }

    public Score calculateScore(Color color) {
        return board.makeScore(color);
    }

    public Map<Point, Piece> getBoard() {
        return board.getBoard();
    }
}
