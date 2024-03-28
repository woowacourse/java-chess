package chess.model.evaluation;

import chess.model.position.Position;
import chess.model.position.File;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class PawnValue implements PieceValue {
    public static final int SPECIAL_POINT_THRESHOLD = 1;

    private final double point;
    private final double specialPoint;

    public PawnValue(double point, double specialPoint) {
        this.point = point;
        this.specialPoint = specialPoint;
    }

    @Override
    public double calculateScore(List<Position> positions) {
        Map<File, Long> countByFile = positions.stream()
                .collect(groupingBy(Position::getFile, counting()));

        return countByFile.values()
                .stream()
                .mapToDouble(this::scoreOfCount)
                .sum();
    }

    private double scoreOfCount(long count) {
        if (count > SPECIAL_POINT_THRESHOLD) {
            return count * specialPoint;
        }
        return count * point;
    }
}
