package chess.dto;

import chess.domain.Movement;
import chess.view.GameCommand;
import chess.view.PositionCommand;

public class CommandDto {
    private final GameCommand gameCommand;
    private final String source;
    private final String target;

    public CommandDto(GameCommand gameCommand, String source, String target) {
        this.gameCommand = gameCommand;
        this.source = source;
        this.target = target;
    }

    public GameCommand gameCommand() {
        return gameCommand;
    }

    public Movement toDomain() {
        return new Movement(PositionCommand.generate(source), PositionCommand.generate(target));
    }
}
