package domain.chessgame;

import domain.squarestatus.SquareStatus;
import domain.type.EmptyType;
import domain.type.PieceType;
import domain.type.Type;

import java.util.Arrays;
import java.util.List;

public enum ScoreCalculator {

    KING(PieceType.KING, 0.0),
    QUEEN(PieceType.QUEEN, 9.0),
    KNIGHT(PieceType.KNIGHT, 2.5),
    BISHOP(PieceType.BISHOP, 3.0),
    ROOK(PieceType.ROOK, 5.0),
    PAWN(PieceType.PAWN, 0.5),
    EMPTY(EmptyType.EMPTY, 0.0);

    private final Type type;
    private final double score;

    ScoreCalculator(final Type type, final double score) {
        this.type = type;
        this.score = score;
    }

    public static double calculateScore(final List<SquareStatus> pieces, final List<Long> pawns) {
        return calculatePieceScore(pieces) + calculatePawnScore(pawns);
    }

    private static double calculatePieceScore(final List<SquareStatus> pieces) {
        return pieces.stream()
                .map(SquareStatus::getType)
                .map(ScoreCalculator::from)
                .mapToDouble(scoreCalculator -> scoreCalculator.score)
                .sum();
    }

    private static double calculatePawnScore(final List<Long> pawns) {
        return pawns.stream()
                .mapToDouble(ScoreCalculator::convertSameColumn)
                .sum();
    }

    private static double convertSameColumn(final long pawnCount) {
        if (pawnCount > 1) {
            return pawnCount * PAWN.score;
        }
        return pawnCount;
    }

    private static ScoreCalculator from(final Type type) {
        return Arrays.stream(values())
                .filter(value -> value.type == type)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("일치하는 기물이 없습니다."));
    }

}
