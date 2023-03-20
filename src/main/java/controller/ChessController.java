package controller;

import common.ExecuteContext;
import domain.Board;
import domain.ChessGame;
import domain.Location;
import java.util.List;
import view.InputView;
import view.OutputView;

public class ChessController {

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

    private boolean command(final List<String> commands) {
        final Command command = Command.find(commands.get(0));
        if (command.equals(Command.END)) {
            return false;
        }
        if (command.equals(Command.START)) {
            chessGame.initialize();
            return true;
        }
        move(commands);
        return true;
    }

    private void move(final List<String> commands) {
        final Location start = mapToLocation(commands.get(1));
        final Location end = mapToLocation(commands.get(2));
        chessGame.move(start, end);
    }

    private Location mapToLocation(final String location) {
        final String columnInput = location.substring(0, 1);
        final String rowInput = location.substring(1);
        try {
            int col = ColumnConverter.findColumn(columnInput);
            int row = Integer.parseInt(rowInput) - 1;
            return Location.of(col, row);
        } catch (Exception exception) {
            throw new IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
        }
    }
}
