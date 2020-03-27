package chess.controller.dto;

import java.util.Collections;
import java.util.List;

public class PositionDto {
    private final List<String> positions;

    public PositionDto(final List<String> positions) {
        this.positions = positions;
    }

    public List<String> getPositions() {
        return Collections.unmodifiableList(positions);
    }
}