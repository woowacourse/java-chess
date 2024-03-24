package chess.domain;

import java.util.List;

public class Command {
    private static final String MOVE_COMMAND = "move";
    public static final String INVALID_MOVE_COMMAND = "부적절한 명령어입니다. move b2 b3와 같이 입력해주세요.";
    public static final String START_COMMAND = "start";
    public static final String END_COMMAND = "end";

    private final List<String> commands;

    public Command(final List<String> commands) {
        validateMoveCommand(commands.get(0));
        this.commands = List.copyOf(commands);
    }

    private void validateMoveCommand(final String command) {
        if (!command.equals(Command.MOVE_COMMAND) && !command.equals(Command.END_COMMAND)) {
            throw new IllegalArgumentException(INVALID_MOVE_COMMAND);
        }
    }

    public boolean isEnd() {
        return commands.get(0).equals(END_COMMAND);
    }

    public String sourceSquare() {
        return commands.get(1);
    }

    public String targetSquare() {
        return commands.get(2);
    }
}
