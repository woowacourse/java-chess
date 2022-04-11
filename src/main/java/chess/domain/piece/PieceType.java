package chess.domain.piece;

import java.util.Map;

import chess.domain.Color;
import chess.domain.board.LineNumber;
import chess.domain.board.Point;

public enum PieceType {

    BISHOP(3),
    KING(0),
    KNIGHT(2.5),
    QUEEN(9),
    ROOK(5),
    PAWN(1),
    EMPTY(0);

    private final double score;

    PieceType(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public static double calculateScore(Map<Point, Piece> pointPieces, Color color) {
        double totalScore = 0;
        for (int verticalIndex = LineNumber.MIN; verticalIndex <= LineNumber.MAX; verticalIndex++) {
            totalScore = calculateVerticalScore(pointPieces, color, totalScore, verticalIndex);
        }
        return totalScore;
    }

    private static double calculateVerticalScore(Map<Point, Piece> pointPieces, Color color, double totalScore,
        int verticalIndex) {
        int pawnCount = 0;
        for (int horizontalIndex = LineNumber.MIN; horizontalIndex <= LineNumber.MAX; horizontalIndex++) {
            Piece piece = pointPieces.get(Point.of(verticalIndex, horizontalIndex));
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
        if (pawnCount > 1) {
            totalScore -= (pawnCount * 0.5);
        }
        return totalScore;
    }
}
