package chess.domain.command;

import java.util.List;

public record CommandCondition(String command, List<String> args) {
    private static final int COMMAND_INDEX = 0;
    private static final int ARGS_SOURCE_INDEX = 0;
    private static final int ARGS_TARGET_INDEX = 1;

    public static CommandCondition of(List<String> inputCommand) {
        String command = inputCommand.get(COMMAND_INDEX);
        List<String> args = inputCommand.subList(COMMAND_INDEX + 1, inputCommand.size());

        return new CommandCondition(command, args);
    }

    public String getSource() {
        return args.get(ARGS_SOURCE_INDEX);
    }

    public String getTarget() {
        return args.get(ARGS_TARGET_INDEX);
    }
}
