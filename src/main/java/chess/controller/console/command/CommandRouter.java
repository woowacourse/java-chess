package chess.controller.console.command;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public enum CommandRouter {
    START("start", Start::new),
    END("end", End::new),
    MOVE("move", Move::new),
    STATUS("status", Status::new);

    private static final int COMMAND_INDEX = 0;

    private final String command;
    private Function<List<String>, Command> commandFactory;

    CommandRouter(String command, Function<List<String>, Command> commandFactory) {
        this.command = command;
        this.commandFactory = commandFactory;
    }

    public static Command findByInputCommand(List<String> command) {
        return Arrays.stream(values())
                .filter(commandRouter -> commandRouter.command.equalsIgnoreCase(command.get(COMMAND_INDEX)))
                .map(commandRouter -> commandRouter.commandFactory.apply(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("커맨드를 잘 못 입력하셨습니다"));
    }
}
