package model.command;

import constant.ErrorCode;
import exception.InvalidCommandException;
import java.util.List;

public class CommandLine {

    public static final int HEAD_INDEX = 0;
    public static final int CURRENT_POSITION_INDEX = 0;
    public static final int NEXT_POSITION_INDEX = 1;

    private final Command head;
    private final List<String> body;

    private CommandLine(final Command head, final List<String> body) {
        this.head = head;
        this.body = body;
    }

    public static CommandLine from(final List<String> input) {
        validateEmpty(input);
        validateCommand(input);
        Command command = Command.from(input.get(HEAD_INDEX));
        validateSize(command, input);
        return new CommandLine(command, input.subList(1, input.size()));
    }

    private static void validateEmpty(final List<String> input) {
        if (input == null || input.isEmpty()) {
            throw new InvalidCommandException(ErrorCode.INVALID_COMMAND);
        }
    }

    private static void validateCommand(final List<String> input) {
        input.forEach(Command::from);
    }

    private static void validateSize(final Command command, final List<String> input) {
        if (!command.isEqualToBodySize(input.size() - 1)) {
            throw new InvalidCommandException(ErrorCode.INVALID_COMMAND);
        }
    }

    public boolean isStart() {
        return head == Command.START;
    }

    public boolean isEnd() {
        return head == Command.END;
    }

    public boolean isMove() {
        return head == Command.MOVE;
    }

    public List<String> getBody() {
        return body;
    }
}
