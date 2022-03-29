package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Color;

public class Status {

    private static final int TERMINATE_GAME = -1;

    private final double whiteScore;
    private final double blackScore;

    public Status(final Board board) {
        whiteScore = calculateScore(board, Color.WHITE);
        blackScore = calculateScore(board, Color.BLACK);
    }

    private double calculateScore(final Board board, final Color color) {
        if (board.isEnd(color)) {
            return TERMINATE_GAME;
        }

        return board.calculateScore(board, color);
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
