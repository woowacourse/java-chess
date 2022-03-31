package chess.domain;

public enum Command {

    START,
    MOVE,
    END;

    private static final String FIRST_MOVE_COMMAND_EXCEPTION_MESSAGE = "체스판을 초기화한 후에 이동 명령을 내려주세요.";
    private static final String IN_GAME_COMMAND_EXCEPTION_MESSAGE = "게임이 이미 진행중입니다.";
    private static final String COMMAND_NOT_FOUND_EXCEPTION_MESSAGE = "존재하지 않는 명령어입니다.";

    public static Command from(String input) {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(COMMAND_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    public static Command firstCommand(String input) {
        Command command = Command.from(input);

        if (command == MOVE) {
            throw new IllegalArgumentException(FIRST_MOVE_COMMAND_EXCEPTION_MESSAGE);
        }
        return command;
    }

    public static Command inGameCommand(String input) {
        Command command = Command.from(input);

        if (command == START) {
            throw new IllegalArgumentException(IN_GAME_COMMAND_EXCEPTION_MESSAGE);
        }
        return command;
    }
}

