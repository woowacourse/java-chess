package command.start;

import java.util.Arrays;
import java.util.List;

public enum StartCommandType {
    START,
    LOAD,
    END;

    private static final int COMMAND_HEAD = 0;

    public static StartAction from(List<String> commands) {
        String command = commands.get(COMMAND_HEAD);
        List<String> parameter = commands.subList(COMMAND_HEAD + 1, commands.size());
        validateCommand(command);
        if (START.name().equals(command.toUpperCase())) {
            return new StartCommand(parameter);
        }
        if (LOAD.name().equals(command.toUpperCase())) {
            return new LoadCommand(parameter);
        }
        return new EndCommand(parameter);
    }

    private static void validateCommand(String command) {
        if (Arrays.stream(StartCommandType.values())
                .anyMatch(startCommandType -> startCommandType.name().equals(command.toUpperCase()))) {
            return;
        }
        throw new IllegalArgumentException("존재하지 않는 커맨드입니다.");
    }
}
