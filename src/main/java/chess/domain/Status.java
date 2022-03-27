package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import java.util.Arrays;

public class Status {

    private static final int NO_KING_EXIST = 0;
    private static final int TERMINATE_GAME = -1;
    private static final double PAWN_MINUS_SCORE = 0.5;

    private final double whiteScore;
    private final double blackScore;

    public Status(Board board) {
        whiteScore = calculateScore(board, Color.WHITE);
        blackScore = calculateScore(board, Color.BLACK);
    }

    private double calculateScore(Board board, Color color) {
        if (board.countPiece(PieceType.KING, color) == NO_KING_EXIST) {
            return TERMINATE_GAME;
        }

        double score = Arrays.stream(PieceType.values())
                .mapToDouble(piece -> piece.calculateScore(board.countPiece(piece, color)))
                .sum();
        return score - board.countDeductedPawns(color) * PAWN_MINUS_SCORE;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
