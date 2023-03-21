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
        Command command;

        do {
            command = getCommend();
        } while (command.canContinue());
    }

    private Command getCommend() {
        try {
            outputView.printGameStartMessage();
            List<String> commands = inputView.readCommand();
            Command command = Command.of(commands);
            processStartGame(command);
            return command;
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return getCommend();
        }
    }

    private void processStartGame(Command command) {
        if (command.isStart()) {
            playGame(new ChessGame());
        }
        if (command.isMove()) {
            throw new IllegalArgumentException("[ERROR] 아직 게임을 시작하지 않았습니다.");
        }
    }

    private void playGame(ChessGame chessGame) {
        Command command;

        do {
            command = getPlayCommand(chessGame);
            System.out.println(command);
        } while (command.canContinue());
    }

    private Command getPlayCommand(ChessGame chessGame) {
        try {
            outputView.printBoard(chessGame.getBoard());
            List<String> commands = inputView.readCommand();
            Command command = Command.of(commands);
            processPlayGame(command, commands, chessGame);
            return command;
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return getPlayCommand(chessGame);
        }
    }

    private void processPlayGame(Command command, List<String> commands, ChessGame chessGame) {
        if (command.isStart()) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 시작되었습니다.");
        }
        if (command.isMove()) {
            processMove(commands, chessGame);
        }
    }

    private void processMove(final List<String> commands, final ChessGame chessGame) {
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
