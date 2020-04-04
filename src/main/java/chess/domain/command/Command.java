package chess.domain.command;

import chess.domain.board.Position;
import chess.domain.player.User;

import java.util.Collections;
import java.util.List;

public class Command {

    private static final int COMMAND_TYPE_INDEX = 0;
    private static final int FLAG_START_INDEX = 1;
    private static final int FIRST_USER_INDEX = 0;
    private static final int SECOND_USER_INDEX = 1;
    private static final int SOURCE_INDEX = 2;
    private static final int TARGET_INDEX = 3;

    private final CommandType command;
    private final List<String> flags;

    private Command(CommandType command, List<String> flags) {
        this.command = command;
        this.flags = flags;
    }

    public static Command from(List<String> input) {
        CommandType commandType = CommandType.from(input.get(COMMAND_TYPE_INDEX));
        commandType.validateCommandSize(input.size());

        List<String> flags = input.subList(FLAG_START_INDEX, input.size());

        return new Command(commandType, flags);
    }

    public boolean isStart() {
        return command.equals(CommandType.START);
    }

    public boolean isNotEnd() {
        return !command.equals(CommandType.END);
    }

    public boolean isMove() {
        return command.equals(CommandType.MOVE);
    }

    public boolean isStatus() {
        return command.equals(CommandType.STATUS);
    }

    public Position getSource() {
        validateMoveType();
        return Position.from(flags.get(SOURCE_INDEX));
    }

    public Position getTarget() {
        validateMoveType();
        return Position.from(flags.get(TARGET_INDEX));
    }

    private void validateMoveType() {
        if (command != CommandType.MOVE) {
            throw new UnsupportedOperationException("move 명령만 사용할 수 있습니다.");
        }
    }

    public User getFirstUser() {
        return new User(flags.get(FIRST_USER_INDEX));
    }

    public User getSecondUser() {
        return new User(flags.get(SECOND_USER_INDEX));
    }

    public List<String> getFlags() {
        return Collections.unmodifiableList(flags);
    }
}
