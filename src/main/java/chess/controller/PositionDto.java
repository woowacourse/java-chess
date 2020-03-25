package chess.controller;

import java.util.List;

public class PositionDto {

    private final List<String> positions;

    public PositionDto(List<String> positions) {
        this.positions = positions;
    }

    public List<String> getPositions() {
        return positions;
    }
}
