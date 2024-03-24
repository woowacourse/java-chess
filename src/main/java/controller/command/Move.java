package controller.command;

import domain.board.Board;
import dto.DtoMapper;
import java.util.StringTokenizer;
import java.util.function.Supplier;
import view.OutputView;

public final class Move extends Command {
    private final String source;
    private final String target;

    public Move(final String source, final String target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public Command execute(final Board board, final Supplier<String> input) {
        moveBoard(board);
        return retryNext(input);
    }

    @Override
    protected Command next(final Supplier<String> input) {
        final String command = input.get();

        if (START_PATTERN.matcher(command).matches()) {
            throw new IllegalArgumentException(String.format("> 입력된 명령어 %s, 진행 상태에서는 시작할 수 없습니다.", command));
        }
        if (MOVE_PATTERN.matcher(command).matches()) {
            final StringTokenizer stringTokenizer = skipFirstToken(command);
            return new Move(stringTokenizer.nextToken(), stringTokenizer.nextToken());
        }
        if (END_PATTERN.matcher(command).matches()) {
            return new End();
        }
        throw new IllegalArgumentException(String.format(COMMAND_ERROR_MESSAGE, command));
    }

    private void moveBoard(final Board board) {
        try {
            board.move(source, target);
            OutputView.printChessBoard(DtoMapper.generateBoardResponse(board));
        } catch (final IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }
}
