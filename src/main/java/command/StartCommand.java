package command;

import java.util.Arrays;

public enum StartCommand {
    START,
    END;

    public static StartCommand from(final String command) {
        validateCommand(command);
        return StartCommand.valueOf(command.toUpperCase());
    }

    private static void validateCommand(String command) {
        if (Arrays.stream(StartCommand.values())
                .anyMatch(startCommand -> startCommand.name().equals(command.toUpperCase()))) {
            return;
        }
        throw new IllegalArgumentException("존재하지 않는 커맨드입니다.");
    }
}
