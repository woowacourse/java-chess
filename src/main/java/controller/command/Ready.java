package controller.command;

import domain.board.Board;
import java.util.function.Supplier;

public final class Ready extends Command {
    @Override
    public Command execute(final Board board, final Supplier<String> input) {
        return retryNext(input);
    }

    @Override
    protected Command next(final Supplier<String> input) {
        final String command = input.get();
        if (START_PATTERN.matcher(command).matches()) {
            return new Start();
        }
        if (MOVE_PATTERN.matcher(command).matches()) {
            throw new IllegalArgumentException(String.format("> 입력된 명령어: %s%n> 게임 시작 전 말을 움직일 수 없습니다.", command));
        }
        if (END_PATTERN.matcher(command).matches()) {
            return new End();
        }
        throw new IllegalArgumentException(String.format(COMMAND_ERROR_MESSAGE, command));
    }
}
