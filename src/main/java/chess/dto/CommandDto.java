package chess.dto;

import chess.domain.Movement;
import chess.util.GameCommand;
import chess.util.PositionConverter;

public class CommandDto {
    private final GameCommand gameCommand;
    private final String source;
    private final String target;

    public CommandDto() {
        this(GameCommand.END, "", "");
    }

    public CommandDto(GameCommand gameCommand, String source, String target) {
        this.gameCommand = gameCommand;
        this.source = source;
        this.target = target;
    }

    public GameCommand gameCommand() {
        return gameCommand;
    }

    public Movement toMovementDomain() {
        return new Movement(PositionConverter.toPosition(source), PositionConverter.toPosition(target));
    }
}
