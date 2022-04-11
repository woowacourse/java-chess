package chess.database.dto;

import java.util.Objects;

import chess.domain.board.Point;

public class PointDto {

    private final int horizontal;
    private final int vertical;

    private PointDto(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public static PointDto of(Point point) {
        return new PointDto(
            point.getHorizontal(),
            point.getVertical()
        );
    }

    public static PointDto of(int horizontal, int vertical) {
        return new PointDto(horizontal, vertical);
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PointDto pointDto = (PointDto)o;
        return horizontal == pointDto.horizontal && vertical == pointDto.vertical;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, vertical);
    }
}
