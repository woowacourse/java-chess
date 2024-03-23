package chess.dto;

import chess.domain.Movement;
import chess.view.PositionConverter;

public class MovementDto {
    private final Movement movement;

    public MovementDto(String source, String target) {
        this.movement = new Movement(PositionConverter.generate(source), PositionConverter.generate(target));
    }

    public Movement movement() {
        return movement;
    }
}
