package chess.vo;

import java.util.Objects;

public final class PointsVO {
    private final String start_point;
    private final String end_point;

    public PointsVO(final String start_point, final String end_point) {
        this.start_point = start_point;
        this.end_point = end_point;
    }

    public String getStartPoint() {
        return start_point;
    }

    public String getEndPoint() {
        return end_point;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PointsVO pointsVO = (PointsVO) o;
        return Objects.equals(start_point, pointsVO.start_point) && Objects.equals(end_point, pointsVO.end_point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start_point, end_point);
    }
}
