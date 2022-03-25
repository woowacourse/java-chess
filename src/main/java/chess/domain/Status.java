package chess.domain;

import java.util.Arrays;

import chess.domain.board.Board;
import chess.domain.piece.Color;

public class Status {

    private final double whiteScore;
    private final double blackScore;

    public Status(Board board) {
        whiteScore = calculateScore(board, Color.WHITE);
        blackScore = calculateScore(board, Color.BLACK);
    }

    private double calculateScore(Board board, Color color) {
        if (board.countPiece(PieceKind.KING, color) == 0) {
            return -1;
        }
        return Arrays.stream(PieceKind.values())
            .mapToDouble(piece -> piece.calculateScore(board.countPiece(piece, color)))
            .sum();
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
