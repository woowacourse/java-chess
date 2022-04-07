package chess.dto.response;

import chess.domain.position.Position;

public class PositionDto {
    private final int xAxis;
    private final int yAxis;

    private PositionDto(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public static PositionDto from(Position position) {
        int xAxis = position.getXAxis().getValue();
        int yAxis = position.getYAxis().getValue();

        return new PositionDto(xAxis, yAxis);
    }

    public Position toPosition() {
        return Position.of(xAxis, yAxis);
    }

    @Override
    public String toString() {
        return "PositionDto{" +
                "xAxis=" + xAxis +
                ", yAxis=" + yAxis +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PositionDto that = (PositionDto) o;

        if (xAxis != that.xAxis) {
            return false;
        }
        return yAxis == that.yAxis;
    }

    @Override
    public int hashCode() {
        int result = xAxis;
        result = 31 * result + yAxis;
        return result;
    }
}
