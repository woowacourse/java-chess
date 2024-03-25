package chess.machine;

import java.util.Arrays;
import java.util.function.Function;

public class CommandMapper {

    public Command inputToCommand(String input) {
        return CommandMatcher.findCommand(input);
    }

    private enum CommandMatcher {
        START(Start::of, "start"),
        MOVE(Move::of, "move"),
        STOP(End::of, "end"),
        STATUS(Status::of, "status");

        private final Function<String, ? extends Command> commandFunction;
        private final String inputCommand;

        CommandMatcher(Function<String, ? extends Command> commandFunction, String inputCommand) {
            this.commandFunction = commandFunction;
            this.inputCommand = inputCommand;
        }

        private static Command findCommand(String inputCommand) {
            return Arrays.stream(values())
                    .filter(value -> inputCommand.startsWith(value.inputCommand))
                    .map(value -> value.commandFunction)
                    .map(function -> function.apply(inputCommand))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Command 입력입니다"));
        }
    }
}
