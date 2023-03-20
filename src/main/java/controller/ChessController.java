package controller;

import common.ExecuteContext;
import domain.Board;
import domain.ChessGame;
import domain.Location;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import view.InputView;
import view.OutputView;

public class ChessController {

    private static final int START_LOCATION_INDEX = 0;
    private static final int END_LOCATION_INDEX = 1;
    private static final int VALID_LOCATION_COMMANDS_SIZE = 3;
    private static final int LOCATION_COMMAND_BEGIN_INDEX = 1;
    private static final String END_COMMAND = "end";
    private static final String START_COMMAND = "start";
    private static final String INVALID_INPUT_ERROR_MESSAGE = "입력이 잘못 되었습니다. 다시 입력해 주세요.";
    private final ChessGame chessGame;
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final ChessGame chessGame, final InputView inputView, final OutputView outputView) {
        this.chessGame = chessGame;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        if (inputView.getEndIntent()) {
            return;
        }
        chessGame.initialize();
        ExecuteContext.repeatableExecute(this::printBoard,
            () -> command(inputView.getCommand()));
    }

    private Void printBoard() {
        final Board board = chessGame.getBoard();
        outputView.printBoard(board);
        return null;
    }

    private boolean command(final List<String> command) {
        if (command.contains(END_COMMAND)) {
            return false;
        }
        if (command.contains(START_COMMAND)) {
            chessGame.initialize();
            return true;
        }
        move(command);
        return true;
    }

    private void move(final List<String> commands) {
        final List<Location> startAndEndLocation = makeStartAndEndLocationByCommands(commands);
        Location start = startAndEndLocation.get(START_LOCATION_INDEX);
        Location end = startAndEndLocation.get(END_LOCATION_INDEX);
        chessGame.move(start, end);
    }

    private List<Location> makeStartAndEndLocationByCommands(final List<String> commands) {
        if (commands.size() != VALID_LOCATION_COMMANDS_SIZE) {
            throw new IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
        }
        return IntStream.range(LOCATION_COMMAND_BEGIN_INDEX, VALID_LOCATION_COMMANDS_SIZE).mapToObj(index -> {
            final String locationInput = commands.get(index);
            return getLocationByInput(locationInput);
        }).collect(Collectors.toList());
    }

    private Location getLocationByInput(final String location) {
        String columnInput = location.substring(0, 1);
        String rowInput = location.substring(1);
        try {
            int col = ColumnConverter.findColumn(columnInput);
            int row = Integer.parseInt(rowInput) - 1;
            return Location.of(col, row);
        } catch (Exception exception) {
            throw new IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
        }
    }
}
