package chess.dto;

import chess.Position;
import java.util.List;

public class PositionResponseDto {

    private final List<Position> positions;

    private PositionResponseDto(final List<Position> positions) {
        this.positions = List.copyOf(positions);
    }

    public static PositionResponseDto of() {
        return new PositionResponseDto(Position.init());
    }

    public List<Position> getPositions() {
        return positions;
    }
}
