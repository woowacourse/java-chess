package chessgame.controller;

import chessgame.domain.board.ChessGame;
import chessgame.domain.piece.Coordinate;
import chessgame.view.InputView;
import chessgame.view.OutputView;

import java.util.List;

public class ChessController {

    public static final char ASCII_ALPHABET_A = 'a';
    public static final int START_COORDINATE_INDEX = 1;
    public static final int COLUMN_INDEX = 0;
    public static final int ROW_INDEX = 1;
    public static final int END_COORDINATE_INDEX = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            startChessGame();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
        }
    }

    private void startChessGame() {
        ChessGame chessGame = new ChessGame();
        Command command = Command.START;
        inputView.printGameStartMessage();
        while (command.canContinue()) {
            List<String> commands = inputView.readCommand();
            command = Command.of(commands);
            carryOutByCommand(chessGame, commands, command);
            outputView.printBoard(chessGame.getBoard());
        }
    }

    private void carryOutByCommand(ChessGame chessGame, List<String> commands, Command command) {
        if (command.isStart()) {
            chessGame.getBoard()
                     .initialize();
            return;
        }
        moveIfCommandIsNotEnd(chessGame, commands, command);
    }

    private void moveIfCommandIsNotEnd(final ChessGame chessGame, final List<String> commands,
                                       final Command command) {
        if (command.isEnd()) {
            return;
        }
        Coordinate startCoordinate = convertCoordinate(commands.get(START_COORDINATE_INDEX));
        Coordinate endCoordinate = convertCoordinate(commands.get(END_COORDINATE_INDEX));
        chessGame.move(startCoordinate, endCoordinate);
    }

    private Coordinate convertCoordinate(final String frontCoordinate) {
        int row = Character.getNumericValue(frontCoordinate.charAt(ROW_INDEX)) - 1;
        int column = (int) frontCoordinate.charAt(COLUMN_INDEX) - ASCII_ALPHABET_A;
        return new Coordinate(row, column);
    }
}
