package chess.model.game;

import chess.model.position.ChessPosition;

import java.util.List;

public class CommonValue implements PieceValue{
    private final double point;

    public CommonValue(double point) {
        this.point = point;
    }

    @Override
    public double calculateScore(List<ChessPosition> positions) {
        return positions.size() * point;
    }
}
