package status;

import domain.board.Board;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.List;

abstract class Turn implements Status {
    private static final int COMMAND_OFFSET = 0;
    private static final int SOURCE_OFFSET = 1;
    private static final int DESTINATION_OFFSET = 2;
    private static final String NO_SUCH_FILE = "열은 A ~ H 를 입력해야 합니다.";
    private static final String NO_SUCH_RANK = "행은 1 ~ 8 을 입력해야 합니다.";
    private static final String NOT_MOVE_COMMAND = "move 명령어만 가능합니다.";
    private static final int FILE_OFFSET = 0;
    private static final int RANK_OFFSET = 1;

    protected final Board board;

    public Turn(final Board board) {
        this.board = board;
    }

    protected abstract void validateTurn(final Position source);

    protected abstract Status getNextTurn();

    @Override
    public final void run() {
        final List<String> gameOption = InputView.readPlayGameOption();
        final Command command = Command.from(gameOption.get(COMMAND_OFFSET));

        execute(gameOption, command);
    }

    private void execute(final List<String> gameOption, final Command command) {
        if (Command.END.equals(command)) {
            new End().run();
            return;
        }

        if (Command.START.equals(command)) {
            throw new IllegalArgumentException(NOT_MOVE_COMMAND);
        }

        play(gameOption);
    }

    private void play(final List<String> gameOption) {
        final Position source = getPositionFrom(gameOption, SOURCE_OFFSET);
        final Position destination = getPositionFrom(gameOption, DESTINATION_OFFSET);
        validateTurn(source);

        board.move(source, destination);
        OutputView.printBoard(board.getPieces());

        getNextTurn().run();
    }

    private static Position getPositionFrom(final List<String> gameOption, int target) {
        final String inputPosition = gameOption.get(target);

        String fileInput = String.valueOf(inputPosition.charAt(FILE_OFFSET));
        String rankInput = String.valueOf(inputPosition.charAt(RANK_OFFSET));

        return Position.of(parseFile(fileInput), parseRank(rankInput));
    }

    private static File parseFile(String input) {
        return Arrays.stream(File.values())
                .filter(file -> !File.NOTHING.equals(file))
                .filter(file -> file.getName().equalsIgnoreCase(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NO_SUCH_FILE));
    }

    private static Rank parseRank(String input) {
        return Arrays.stream(Rank.values())
                .filter(rank -> !Rank.NOTHING.equals(rank))
                .filter(rank -> rank.getName().equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NO_SUCH_RANK));
    }
}
