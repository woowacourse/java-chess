package chess.domain.position.direction;

import chess.domain.position.Position;

public class DiagonalDirection implements Direction{

    public boolean isOnDiagonal(Position from, Position other) {
        int xAxisDelta = Math.abs(other.getXAxis().getValue() - from.getXAxis().getValue());
        int yAxisDelta = Math.abs(other.getYAxis().getValue() - from.getYAxis().getValue());

        return xAxisDelta == yAxisDelta;
    }

    @Override
    public boolean isOnDirection(Position from, Position to) {
        return isOnDiagonal(from, to);
    }
}
