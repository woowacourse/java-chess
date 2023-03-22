import domain.board.Board;
import domain.board.InitialChessAlignment;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.List;

public final class Application {
    private static final int COMMAND_OFFSET = 0;
    private static final int SOURCE_OFFSET = 1;
    private static final int DESTINATION_OFFSET = 2;
    private static final String NOT_STARTED = "게임을 먼저 시작해야 합니다.";
    private static final String NO_SUCH_FILE = "열은 A ~ H 를 입력해야 합니다.";
    private static final String NO_SUCH_RANK = "행은 1 ~ 8 을 입력해야 합니다.";

    public static void main(String[] args) {
        try {
            run();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private static void run() {
        if (isStart()) {
            final Board board = Board.create(new InitialChessAlignment());
            OutputView.printBoard(board.getPieces());

            play(board);
        }
    }

    private static boolean isStart() {
        final String commandInput = InputView.readStartGameOption();
        final Command command = Command.from(commandInput);

        if (Command.END.equals(command)) {
            return false;
        }
        if (Command.MOVE.equals(command)) {
            throw new IllegalArgumentException(NOT_STARTED);
        }
        return true;
    }

    private static void play(final Board board) {
        final List<String> gameOption = InputView.readPlayGameOption();
        final Command command = Command.from(gameOption.get(COMMAND_OFFSET));

        if (Command.END.equals(command)) {
            return;
        }

        board.move(getSourcePiece(gameOption), getDestination(gameOption));
        OutputView.printBoard(board.getPieces());

        play(board);
    }

    private static Position getDestination(final List<String> gameOption) {
        final String destination = gameOption.get(DESTINATION_OFFSET);
        return parseToPosition(destination);
    }

    private static Position getSourcePiece(final List<String> gameOption) {
        final String source = gameOption.get(SOURCE_OFFSET);
        return parseToPosition(source);
    }

    private static Position parseToPosition(String input) {
        String fileInput = String.valueOf(input.charAt(0));
        String rankInput = String.valueOf(input.charAt(1));

        return Position.of(parseToFile(fileInput), parseToRank(rankInput));
    }

    private static File parseToFile(String input) {
        return Arrays.stream(File.values())
                .filter(file -> !File.NOTHING.equals(file))
                .filter(file -> file.getName().equalsIgnoreCase(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NO_SUCH_FILE));
    }

    private static Rank parseToRank(String input) {
        return Arrays.stream(Rank.values())
                .filter(rank -> !Rank.NOTHING.equals(rank))
                .filter(rank -> rank.getName().equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NO_SUCH_RANK));
    }
}
