package chess.domain;

public enum Command {

    START("start"),
    MOVE("move"),
    END("end");

    private static final String FIRST_MOVE_COMMAND_EXCEPTION_MESSAGE = "체스판을 초기화한 후에 이동 명령을 내려주세요.";

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command from(String input) {
        return Command.valueOf(input.toUpperCase());
    }

    public static Command firstCommand(String input) {
        Command command = Command.from(input);

        if (command == MOVE) {
            throw new IllegalArgumentException(FIRST_MOVE_COMMAND_EXCEPTION_MESSAGE);
        }
        return command;
    }
}

