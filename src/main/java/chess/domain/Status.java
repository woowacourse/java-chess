package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import java.util.Arrays;

public class Status {

    private final double whiteScore;
    private final double blackScore;

    public Status(Board board) {
        whiteScore = calculateScore(board, Color.WHITE);
        blackScore = calculateScore(board, Color.BLACK);
    }

    private double calculateScore(Board board, Color color) {
        if (board.countPiece(PieceType.KING, color) == 0) {
            return -1;
        }

        double score = Arrays.stream(PieceType.values())
                .mapToDouble(piece -> piece.calculateScore(board.countPiece(piece, color)))
                .sum();
        return score - board.countDeductedPawns(color) * 0.5;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
