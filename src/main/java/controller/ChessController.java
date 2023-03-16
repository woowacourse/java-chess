package controller;

import domain.board.Board;
import domain.piecetype.Coordinate;
import view.InputView;
import view.OutputView;

import java.util.List;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Board board = new Board();
        Command command = Command.START;
        while (command.isContinue()) {
            List<String> commands = inputView.readCommand();
            command = Command.of(commands);
            carryOutByCommand(board, commands, command);
            outputView.printBoard(board);
        }
    }

    public void carryOutByCommand(Board board, List<String> commands, Command command) {
        if (command.isStart()) {
            board.initialize();
            return;
        }
        moveIfCommandIsNotEnd(board, commands, command);
    }

    private void moveIfCommandIsNotEnd(final Board board, final List<String> commands, final Command command) {
        if (command.isEnd()) {
            return;
        }
        Coordinate startCoordinate = convertCoordinate(commands.get(1));
        Coordinate endCoordinate = convertCoordinate(commands.get(2));
        board.move(startCoordinate, endCoordinate);
    }

    private Coordinate convertCoordinate(final String frontCoordinate) {
        int row = Character.getNumericValue(frontCoordinate.charAt(1)) - 1;
        int col = (int) frontCoordinate.charAt(0) - 97;
        return new Coordinate(row, col);
    }
}
