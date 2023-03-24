package chess.controller.command;

import java.util.ArrayList;
import java.util.List;

public class StartCommand {

    private static final int COMMAND_TYPE_INDEX = 0;

    private final StartCommandType startCommandType;
    private final List<String> commands;

    private StartCommand(final StartCommandType startCommandType, final List<String> commands) {
        validateParameterSize(startCommandType, commands);
        this.startCommandType = startCommandType;
        this.commands = commands;
    }

    private void validateParameterSize(final StartCommandType startCommandType, final List<String> commands) {
        if (!startCommandType.matchSize(commands)) {
            throw new IllegalArgumentException(startCommandType + " 명령어의 파라미터가 올바르지 않습니다.");
        }
    }

    public static StartCommand parse(List<String> inputs) {
        inputs = new ArrayList<>(inputs);
        final String typeInput = inputs.remove(COMMAND_TYPE_INDEX);
        final StartCommandType startCommandType = StartCommandType.find(typeInput);
        return new StartCommand(startCommandType, inputs);
    }

    public Long restartGameId() {
        return Long.parseLong(commands.get(0));
    }

    public StartCommandType type() {
        return startCommandType;
    }
}
