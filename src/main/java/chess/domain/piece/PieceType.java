package chess.domain.piece;

import chess.domain.board.LineNumber;
import chess.domain.board.Point;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;

public enum PieceType {

    BISHOP(3),
    KING(0),
    KNIGHT(2.5),
    QUEEN(9),
    ROOK(5),
    PAWN(1),
    EMPTY(0);

    private static final int PAWN_SCORE_REDUCTION_STANDARD = 1;
    private static final double PAWN_REDUCTION_SCORE = 0.5;
    private final double score;

    PieceType(double score) {
        this.score = score;
    }

    public static PieceType convertTypeByString(String type) {
        return Arrays.stream(values())
                .filter(value -> value.toString().equals(type))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public double getScore() {
        return score;
    }

    public static double calculateScore(Map<Point, Piece> pointPieces, Color color) {
        double totalScore = 0;
        for (int i = LineNumber.MIN; i <= LineNumber.MAX; i++) {
            totalScore = calculateVerticalScore(pointPieces, color, totalScore, i);
        }
        return totalScore;
    }

    private static double calculateVerticalScore(Map<Point, Piece> pointPieces, Color color, double totalScore, int i) {
        int pawnCount = 0;
        for (int j = LineNumber.MIN; j <= LineNumber.MAX; j++) {
            Piece piece = pointPieces.get(Point.of(i, j));
            totalScore += getPieceScore(piece, color);
            pawnCount = getPawnCount(pawnCount, piece, color);
        }
        return adjustPawnScore(totalScore, pawnCount);
    }

    private static double getPieceScore(Piece piece, Color color) {
        if (!piece.isSameColor(color)) {
            return 0;
        }
        return piece.getScore();
    }

    private static int getPawnCount(int pawnCount, Piece piece, Color color) {
        if (piece.isSameColor(color) && piece.isSameType(PieceType.PAWN)) {
            pawnCount++;
        }
        return pawnCount;
    }

    private static double adjustPawnScore(double totalScore, int pawnCount) {
        if (pawnCount > PAWN_SCORE_REDUCTION_STANDARD) {
            totalScore -= (pawnCount * PAWN_REDUCTION_SCORE);
        }
        return totalScore;
    }
}
