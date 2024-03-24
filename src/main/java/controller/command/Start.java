package controller.command;

import domain.board.Board;
import dto.DtoMapper;
import java.util.StringTokenizer;
import java.util.function.Supplier;
import view.OutputView;

public final class Start extends Command {

    @Override
    public Command execute(final Board board, final Supplier<String> input) {
        OutputView.printChessBoard(DtoMapper.generateBoardResponse(board));
        return retryNext(input);
    }

    @Override
    protected Command next(final Supplier<String> input) {
        final String command = input.get();
        if (START_PATTERN.matcher(command).matches()) {
            throw new IllegalArgumentException(String.format("> 입력된 명령어 %s, 이미 시작했습니다.", command));
        }
        if (MOVE_PATTERN.matcher(command).matches()) {
            final StringTokenizer tokens = skipFirstToken(command);
            return new Move(tokens.nextToken(), tokens.nextToken());
        }
        if (END_PATTERN.matcher(command).matches()) {
            return new End();
        }
        throw new IllegalArgumentException(String.format(COMMAND_ERROR_MESSAGE, command));
    }
}
