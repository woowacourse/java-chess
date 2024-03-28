package chess.model.evaluation;

import chess.model.position.Position;
import chess.model.position.File;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class PawnValue implements PieceValue {
    public static final int UNFAVORABLE_POINT_THRESHOLD = 1;

    private final double point;
    private final double unfavorablePoint;

    public PawnValue(double point, double unfavorablePoint) {
        this.point = point;
        this.unfavorablePoint = unfavorablePoint;
    }

    @Override
    public double calculateValue(List<Position> positions) {
        Map<File, Long> countByFile = positions.stream()
                .collect(groupingBy(Position::getFile, counting()));

        return countByFile.values()
                .stream()
                .mapToDouble(this::pointOfCount)
                .sum();
    }

    private double pointOfCount(long count) {
        if (count > UNFAVORABLE_POINT_THRESHOLD) {
            return count * unfavorablePoint;
        }
        return count * point;
    }
}
