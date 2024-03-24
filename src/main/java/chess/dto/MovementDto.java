package chess.dto;

import chess.domain.Movement;
import chess.view.PositionConverter;

public class MovementDto {
    private final boolean isMove;
    private final String source;
    private final String target;

    public MovementDto(boolean isMove, String source, String target) {
        this.isMove = isMove;
        this.source = source;
        this.target = target;
    }

    public boolean isMove() {
        return isMove;
    }

    public Movement movement() {
        return new Movement(PositionConverter.generate(source), PositionConverter.generate(target));
    }
}
