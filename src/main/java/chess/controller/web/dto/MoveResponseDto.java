package chess.controller.web.dto;

import java.util.Objects;

public class MoveResponseDto {
    private final boolean movable;

    public MoveResponseDto(boolean movable) {
        this.movable = movable;
    }

    public boolean isMovable() {
        return movable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoveResponseDto)) return false;
        MoveResponseDto that = (MoveResponseDto) o;
        return isMovable() == that.isMovable();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isMovable());
    }

    @Override
    public String toString() {
        return "MoveResponseDto{" +
                "movable=" + movable +
                '}';
    }
}
