package chess.model.evaluation;

import chess.model.position.Position;

import java.util.List;

public class CommonValue implements PieceValue{
    private final double point;

    public CommonValue(double point) {
        this.point = point;
    }

    @Override
    public double calculateScore(List<Position> positions) {
        return positions.size() * point;
    }
}
