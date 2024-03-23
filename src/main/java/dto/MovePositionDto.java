package dto;

import domain.board.Position;

import java.util.Collections;
import java.util.List;

public record MovePositionDto(List<Position> positions) {

    public static MovePositionDto noPosition() {
        return new MovePositionDto(Collections.emptyList());
    }

    public static MovePositionDto of(final Position source, final Position destination) {
        return new MovePositionDto(List.of(source, destination));
    }

    public boolean hasPosition() {
        return !positions.isEmpty();
    }

    public Position source() {
        return positions.get(0);
    }

    public Position destination() {
        return positions.get(1);
    }
}
