package techcourse.fp.chess.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.LongStream;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.Piece;
import techcourse.fp.chess.domain.piece.PieceType;

public class ScoreCalculator {

    private static final double SCORE_OF_MULTI_PAWN = 0.5;
    private static final int MIN_OF_MULTI_PAWN_COUNT = 2;

    public static double calculate(Map<Position, Piece> board, final Color color) {
        return getSumOfDefaultScore(board, color) - getCountOfMultiPawn(board, color) * SCORE_OF_MULTI_PAWN;
    }

    private static double getSumOfDefaultScore(final Map<Position, Piece> board, final Color color) {
        return board.values().stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private static double getCountOfMultiPawn(final Map<Position, Piece> board, final Color color) {
        return Arrays.stream(File.values())
                .flatMapToLong(file -> LongStream.of(getCountOfPawnByFile(board, color, file)))
                .filter(value -> value >= MIN_OF_MULTI_PAWN_COUNT)
                .sum();
    }

    private static long getCountOfPawnByFile(final Map<Position, Piece> board, final Color color, final File file) {
        return board.keySet().stream()
                .filter(position -> position.getFile() == file)
                .map(position -> board.get(position))
                .filter(piece -> piece.isSameColor(color))
                .filter(piece -> piece.isSamePieceType(PieceType.PAWN))
                .count();
    }
}
