package chess.dto;

import chess.controller.Command;

public class GameRequest {

    private static final String CONSOLE_DELIMITER = " ";

    private final Command command;
    private final Arguments arguments;

    public GameRequest(Command command, Arguments arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public static GameRequest of(String input) {
        validateNull(input);
        String[] split = input.split(CONSOLE_DELIMITER);

        Command command = Command.find(split[0]);
        Arguments arguments = Arguments.ofArray(split, 1);
        return new GameRequest(command, arguments);
    }

    private static void validateNull(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 입력이 잘못되었습니다.");
        }
    }

    public Command getCommand() {
        return command;
    }

    public Arguments getArguments() {
        return arguments;
    }
}
