package command;

import java.util.Arrays;

public enum MoveCommand {
    END,
    MOVE;

    public static MoveCommand from(final String command) {
        validateCommand(command);
        return MoveCommand.valueOf(command.toUpperCase());
    }

    private static void validateCommand(String command) {
        if (Arrays.stream(MoveCommand.values())
                .anyMatch(moveCommand -> moveCommand.name().equals(command.toUpperCase()))) {
            return;
        }
        throw new IllegalArgumentException("존재하지 않는 커맨드입니다.");
    }
}
