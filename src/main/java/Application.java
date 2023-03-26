import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import state.Command;
import state.End;
import state.Ready;
import state.State;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public final class Application {
    private static final int COMMAND_OFFSET = 0;
    private static final int SOURCE_OFFSET = 1;
    private static final int DESTINATION_OFFSET = 2;
    private static final int FILE_OFFSET = 0;
    private static final int RANK_OFFSET = 1;

    private static final String NOT_STARTED = "게임을 먼저 시작해야 합니다.";
    private static final String NO_SUCH_FILE = "열은 A ~ H 를 입력해야 합니다.";
    private static final String NO_SUCH_RANK = "행은 1 ~ 8 을 입력해야 합니다.";
    private static final String NOT_MOVE_OR_END = "move 또는 end 명령어만 가능합니다.";
    private static final String INVALID_MOVE_COMMAND_SIZE = "move b2 b3 등으로 입력해야 합니다.";
    private static final int MOVE_COMMAND_SIZE = 3;

    public static void main(final String[] args) {
        State gameState = initChessGame();

        while (gameState.isPlaying()) {
            gameState = retryOnError(Application::play, gameState);
        }
    }

    private static State retryOnError(final Function<State, State> process, final State gameState) {
        try {
            return process.apply(gameState);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }

        return retryOnError(process, gameState);
    }

    private static State initChessGame() {
        final Command command = Command.from(InputView.readStartOption());

        if (Command.MOVE.equals(command)) {
            throw new IllegalArgumentException(NOT_STARTED);
        }

        return getStartState(command);
    }

    private static State getStartState(final Command command) {
        if (Command.END.equals(command)) {
            return new End();
        }

        final State next = new Ready().next();
        OutputView.printBoard(next.getBoard());

        return next;
    }

    private static State play(State gameState) {
        final List<String> gameOption = InputView.readPlayGameOption();
        if (isEnd(gameOption.get(COMMAND_OFFSET))) {
            return new End();
        }
        if (gameOption.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException(INVALID_MOVE_COMMAND_SIZE);
        }

        return next(gameState, gameOption);
    }

    private static State next(final State gameState, final List<String> gameOption) {
        final Position source = getPositionFrom(gameOption, SOURCE_OFFSET);
        final Position destination = getPositionFrom(gameOption, DESTINATION_OFFSET);

        gameState.move(source, destination);
        OutputView.printBoard(gameState.getBoard());

        return gameState.next();
    }

    private static boolean isEnd(final String commandInput) {
        final Command command = Command.from(commandInput);

        if (Command.MOVE.equals(command)) {
            return false;
        }
        if (Command.END.equals(command)) {
            return true;
        }

        throw new IllegalArgumentException(NOT_MOVE_OR_END);
    }

    private static Position getPositionFrom(final List<String> gameOption, int target) {
        final String inputPosition = gameOption.get(target);

        String fileInput = String.valueOf(inputPosition.charAt(FILE_OFFSET));
        String rankInput = String.valueOf(inputPosition.charAt(RANK_OFFSET));

        return Position.of(parseFile(fileInput), parseRank(rankInput));
    }

    private static File parseFile(String input) {
        try {
            return File.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(NO_SUCH_FILE);
        }
    }

    private static Rank parseRank(String input) {
        final int inputRank = Integer.parseInt(input);

        return Arrays.stream(Rank.values())
                .filter(rank -> !Rank.NOTHING.equals(rank))
                .filter(rank -> inputRank == rank.getValue())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NO_SUCH_RANK));
    }
}
