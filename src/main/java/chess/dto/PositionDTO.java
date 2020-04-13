package chess.dto;

import java.util.Collections;
import java.util.List;

public class PositionDTO {
    private final List<String> positions;

    public PositionDTO(final List<String> positions) {
        this.positions = positions;
    }

    public List<String> getPositions() {
        return Collections.unmodifiableList(positions);
    }
}