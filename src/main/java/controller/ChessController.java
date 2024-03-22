package controller;

import dto.GameBoardDto;
import exception.CustomException;
import java.util.Collections;
import java.util.List;
import model.Command;
import model.GameBoard;
import model.status.GameStatus;
import model.status.Initialization;
import view.InputView;
import view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        GameBoard gameBoard = new GameBoard();
        gameBoard.setting();
        outputView.printStartMessage();
        GameStatus gameStatus = initGame();

        while (gameStatus.isRunning()) {
            printCurrentStatus(gameBoard);
            gameStatus = play(gameStatus, gameBoard);
        }
    }

    private GameStatus initGame() {
        try {
            return Initialization.gameSetting(getCommand());
        } catch (CustomException exception) {
            outputView.printException(exception);
            return initGame();
        }
    }

    private GameStatus play(GameStatus gameStatus, final GameBoard gameBoard) {
        try {
            return gameStatus.play(getCommand(), gameBoard);
        } catch (CustomException exception) {
            outputView.printException(exception);
            return play(gameStatus, gameBoard);
        }
    }

    private void printCurrentStatus(final GameBoard gameBoard) {
        outputView.printGameBoard(GameBoardDto.from(gameBoard));
        outputView.printCurrentCamp(gameBoard.getCamp());
    }

    private List<String> getCommand() {
        List<String> command = Collections.emptyList();
        while (command.isEmpty()) {
            command = readCommand();
        }
        return command;
    }

    private List<String> readCommand() {
        try {
            return readLine();
        } catch (CustomException exception) {
            outputView.printException(exception);
        }
        return Collections.emptyList();
    }

    private List<String> readLine() {
        List<String> input = inputView.readCommandList();
        for (String value : input) {
            Command.validate(value);
        }
        return input;
    }
}
