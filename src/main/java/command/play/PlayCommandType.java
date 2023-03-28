package command.play;

import java.util.Arrays;
import java.util.List;

public enum PlayCommandType {
    END,
    MOVE,
    STATUS,
    SAVE;

    private static final int COMMAND_HEAD = 0;

    public static PlayAction from(final List<String> commands) {
        String command = commands.get(COMMAND_HEAD);
        List<String> parameter = commands.subList(COMMAND_HEAD + 1, commands.size());
        validateCommand(command);
        if (END.name().equals(command.toUpperCase())) {
            return new EndCommand(parameter);
        }
        if (MOVE.name().equals(command.toUpperCase())) {
            return new MoveCommand(parameter);
        }
        if (STATUS.name().equals(command.toUpperCase())) {
            return new StatusCommand(parameter);
        }
        return new SaveCommand(parameter);
    }

    private static void validateCommand(String command) {
        if (Arrays.stream(PlayCommandType.values())
                .anyMatch(playCommand -> playCommand.name().equals(command.toUpperCase()))) {
            return;
        }
        throw new IllegalArgumentException("존재하지 않는 커맨드입니다.");
    }
}
