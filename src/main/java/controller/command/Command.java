package controller.command;


import domain.board.Board;
import java.util.StringTokenizer;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import view.OutputView;

public abstract class Command {
    protected static final Pattern START_PATTERN = Pattern.compile("start");
    protected static final Pattern MOVE_PATTERN = Pattern.compile("move ([a-h][1-8]) ([a-h][1-8])");
    protected static final Pattern END_PATTERN = Pattern.compile("end");
    protected static final String COMMAND_ERROR_MESSAGE = """
            > 입력된 명령어: %s, 정확한 명령어를 입력해주세요
            > 예: 게임 시작: start, 게임 종료: end, 말 이동: move source target""";

    public abstract Command execute(final Board board, final Supplier<String> input);

    protected Command retryNext(final Supplier<String> input) {
        try {
            return next(input);
        } catch (final IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return retryNext(input);
    }

    protected StringTokenizer skipFirstToken(final String command) {
        final StringTokenizer stringTokenizer = new StringTokenizer(command);
        stringTokenizer.nextToken();
        return stringTokenizer;
    }

    protected abstract Command next(final Supplier<String> input);

    public boolean isNotEnd() {
        return true;
    }
}
