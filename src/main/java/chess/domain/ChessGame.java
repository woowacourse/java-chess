package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Piece;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

public class ChessGame {
    public static final int BOARD_SIZE = 8;

    private final Board board;
    private Color currentColor = WHITE;

    public ChessGame() {
        this.board = new Board(initializeBoard());
    }

    public ChessGame(Map<Point, Piece> board, Color currentColor) {
        this.board = new Board(board);
        this.currentColor = currentColor;
    }

    private Map<Point, Piece> initializeBoard() {
        Map<Point, Piece> initialBoard = new HashMap<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            initializeColumn(i, initialBoard);
        }
        return initialBoard;
    }

    private void initializeColumn(int i, Map<Point, Piece> initialBoard) {
        for (int j = 0; j < BOARD_SIZE; j++) {
            initialBoard.put(Point.of(i, j), PieceType.findPiece(i, j));
        }
    }

    public void playTurn(Point source, Point target) {
        board.movePiece(source, target, currentColor);
        this.currentColor = switchTurn();
    }

    private Color switchTurn() {
        if (this.currentColor.isSameAs(WHITE)) {
            return BLACK;
        }
        return WHITE;
    }

    public boolean isProgressing() {
        return board.hasBothKings();
    }

    public Result calculateScore() {
        return board.makeResult();
    }

    public Map<Point, Piece> getBoard() {
        return board.getBoard();
    }

    public Color getCurrentColor() {
        return currentColor;
    }
}
