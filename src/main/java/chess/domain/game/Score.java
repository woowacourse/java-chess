package chess.domain.game;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Score {

    private static final double DUPLICATED_PAWN_DEDUCTION_SCORE = 0.5;

    private final double value;

    private Score(double score) {
        validate(score);
        this.value = score;
    }

    public static Score valueOf(double score) {
        return new Score(score);
    }

    private void validate(double score) {
        if (score < 0) {
            throw new IllegalArgumentException("점수는 음수일 수 없습니다.");
        }
    }

    public static Score calculate(Map<Position, Piece> pieces, Color color) {
        Map<Position, Piece> colorPieces = getColorPieces(pieces, color);
        double sum = calculateSum(colorPieces);
        return new Score(sum);
    }

    private static Map<Position, Piece> getColorPieces(Map<Position, Piece> pieces, Color color) {
        return pieces.entrySet().stream()
                .filter(it -> it.getValue().getColor() == color)
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    private static double calculateSum(Map<Position, Piece> pieces) {
        double pieceScore = pieces.values().stream()
                .map(piece -> piece.getScore().value)
                .mapToDouble(i -> i)
                .sum();
        double deductionScore = getDuplicatedPawnSize(pieces) * DUPLICATED_PAWN_DEDUCTION_SCORE;
        return pieceScore - deductionScore;
    }

    private static int getDuplicatedPawnSize(Map<Position, Piece> pieces) {
        Map<Integer, List<Piece>> fileToPawn = pieces.entrySet().stream()
                .filter(positionToPiece -> positionToPiece.getValue().isSameType(PieceType.PAWN))
                .collect(groupingBy(positionPieceEntry -> positionPieceEntry.getKey().getFileIndex(),
                        mapping(Entry::getValue, Collectors.toList())));

        return fileToPawn.values().stream()
                .filter(it -> it.size() > 1)
                .mapToInt(List::size)
                .sum();
    }

    public static Color judgeWinner(Score blackScore, Score whiteScore) {
        if (blackScore.value > whiteScore.value) {
            return Color.BLACK;
        }
        if (blackScore.value < whiteScore.value) {
            return Color.WHITE;
        }
        return Color.NONE;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Score score = (Score) o;

        return Double.compare(score.value, value) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> 32));
    }
}
