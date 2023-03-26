package chessgame.controller;

import chessgame.domain.chessgame.ChessGame;
import chessgame.domain.coordinate.Coordinate;
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
            command = readCommend();
        } while (command.canContinue());
    }

    private Command readCommend() {
        try {
            outputView.printGameStartMessage();
            List<String> commands = inputView.readCommand();
            Command command = Command.of(commands);
            processStartGame(command);
            return command;
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return readCommend();
        }
    }

    private void processStartGame(final Command command) {
        if (command.isStart()) {
            playGame(new ChessGame());
        }
        if (command.isMove() || command.isStatus()) {
            throw new IllegalArgumentException("[ERROR] 아직 게임을 시작하지 않았습니다.");
        }
    }

    private void playGame(final ChessGame chessGame) {
        Command command;

        do {
            command = readPlayCommand(chessGame);
            printGameResultWhenEnd(command, chessGame);
        } while (command.canContinue());
    }

    private Command readPlayCommand(final ChessGame chessGame) {
        try {
            outputView.printBoard(chessGame.getBoard());
            List<String> commands = inputView.readCommand();
            Command command = Command.of(commands);
            return processPlayGame(command, commands, chessGame);
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return readPlayCommand(chessGame);
        }
    }

    private Command processPlayGame(final Command command,
                                    final List<String> commands,
                                    final ChessGame chessGame) {
        if (command.isStart()) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 시작되었습니다.");
        }
        if (command.isMove()) {
            return processMove(commands, chessGame);
        }
        if (command.isStatus()) {
            printGameStatus(chessGame);
        }
        return command;
    }

    private Command processMove(final List<String> commands, final ChessGame chessGame) {
        Coordinate startCoordinate = convertCoordinate(commands.get(START_COORDINATE_INDEX));
        Coordinate endCoordinate = convertCoordinate(commands.get(END_COORDINATE_INDEX));
        boolean isKing = chessGame.move(startCoordinate, endCoordinate);
        if (isKing) {
            return Command.END;
        }
        return Command.MOVE;
    }

    private Coordinate convertCoordinate(final String frontCoordinate) {
        int row = Character.getNumericValue(frontCoordinate.charAt(ROW_INDEX)) - 1;
        int column = (int) frontCoordinate.charAt(COLUMN_INDEX) - ASCII_ALPHABET_A;
        return Coordinate.fromOnBoard(row, column);
    }

    private void printGameStatus(ChessGame chessGame) {
        outputView.printGameStatus(chessGame.getStatus());
    }

    private void printGameResultWhenEnd(Command command, ChessGame chessGame) {
        if (command.isEnd()) {
            outputView.printGameResult(chessGame.getBoard(), chessGame.getStatus());
        }
    }
}
