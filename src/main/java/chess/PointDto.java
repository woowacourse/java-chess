package chess;

import java.util.Objects;

public class PointDto {
    private String point;

    public PointDto(String point) {
        this.point = point;
    }

    public String getPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PointDto pointDto = (PointDto)o;
        return Objects.equals(point, pointDto.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}
