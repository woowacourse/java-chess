package command;

import java.util.Arrays;

public enum PlayCommand {
    END,
    MOVE,
    STATUS,
    SAVE;

    public static PlayCommand from(final String command) {
        validateCommand(command);
        return PlayCommand.valueOf(command.toUpperCase());
    }

    private static void validateCommand(String command) {
        if (Arrays.stream(PlayCommand.values())
                .anyMatch(playCommand -> playCommand.name().equals(command.toUpperCase()))) {
            return;
        }
        throw new IllegalArgumentException("존재하지 않는 커맨드입니다.");
    }
}
