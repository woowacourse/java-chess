package chess.dto;

import chess.domain.Position;
import chess.view.Command;

public final class CommandDto {
    private static final int GAME_STATE_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final Command command;
    private final Position source;
    private final Position target;

    private CommandDto(final Command command, final Position source, final Position target) {
        this.command = command;
        this.source = source;
        this.target = target;
    }

    public static CommandDto from(String... commands) {
        validateCommandsLength(commands);

        if (commands.length == 1) {
            return new CommandDto(Command.from(commands[GAME_STATE_INDEX]), Position.IS_NULL, Position.IS_NULL);
        }
        return new CommandDto(Command.from(commands[GAME_STATE_INDEX]), Position.from(commands[SOURCE_INDEX]), Position.from(commands[TARGET_INDEX]));
    }

    private static void validateCommandsLength(final String[] commands) {
        if (commands.length != 1 && commands.length != 3) {
            throw new IllegalArgumentException("유효한 입력이 아닙니다.");
        }
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
