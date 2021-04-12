package chess.controller.web.dto.move;

import chess.domain.MoveCommand;

public class MoveRequestDto {

    private final String source;
    private final String target;

    public MoveRequestDto(final String source, final String target) {
        this.source = source;
        this.target = target;
    }

    public MoveCommand toMoveCommand() {
        return new MoveCommand(source, target);
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
