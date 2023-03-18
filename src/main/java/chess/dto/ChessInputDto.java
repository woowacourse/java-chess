package chess.dto;

import chess.domain.Position;
import chess.view.Command;

public class ChessInputDto {
    private final Command command;
    private final Position source;
    private final Position target;

    private ChessInputDto(final Command command, final Position source, final Position target) {
        this.command = command;
        this.source = source;
        this.target = target;
    }

    public static ChessInputDto from(final Command command, final String source, final String target) {
        return new ChessInputDto(command, Position.from(source), Position.from(target));
    }

    public static ChessInputDto from(final Command command) {
        return new ChessInputDto(command, Position.NOT_ABLE, Position.NOT_ABLE);
    }

    public Command getGameState() {
        return command;
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }
}
