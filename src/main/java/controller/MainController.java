package controller;

import static controller.command.CommandConverter.MOVE_COMMAND_DESTINATION_INDEX;
import static controller.command.CommandConverter.MOVE_COMMAND_SOURCE_INDEX;

import java.util.List;
import java.util.function.Supplier;

import controller.command.CommandConverter;
import controller.command.CommandType;
import controller.command.EndCommand;
import controller.command.GameCommand;
import controller.command.MoveCommand;
import controller.command.StartCommand;
import controller.command.StatusCommand;
import database.db.ChessBoardDao;
import domain.ChessBoard;
import domain.Square;
import view.InputView;
import view.OutputView;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ChessBoardDao chessBoardDao;

    public MainController(ChessBoardDao chessBoardDao) {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.chessBoardDao = chessBoardDao;
    }

    public void run() {
        ChessBoard chessBoard = chessBoardDao.read();
        inputView.printStartMessage();
        GameStatus gameStatus = GameStatus.INIT;
        do {
            gameStatus = executeCommand(chessBoard, gameStatus);
            chessBoardDao.update(chessBoard);
        }
        while (gameStatus != GameStatus.END);
    }

    private GameStatus executeCommand(ChessBoard chessBoard, GameStatus gameStatus) {
        try {
            GameCommand gameCommand = getGameCommand(chessBoard);
            return gameCommand.execute(gameStatus);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return executeCommand(chessBoard, gameStatus);
        }
    }

    private GameCommand getGameCommand(ChessBoard chessBoard) {
        try {
            List<String> inputs = readValidInput(inputView::readInput);
            CommandConverter commandConverter = new CommandConverter(inputs);
            CommandType commandType = commandConverter.getCommandType();
            return selectGameCommand(chessBoard, inputs, commandType);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return getGameCommand(chessBoard);
        }
    }

    private List<String> readValidInput(Supplier<List<String>> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return readValidInput(inputReader);
        }
    }

    private GameCommand selectGameCommand(ChessBoard chessBoard, List<String> inputs, CommandType commandType) {
        if (commandType == CommandType.MOVE) {
            Square src = Square.of(inputs.get(MOVE_COMMAND_SOURCE_INDEX));
            Square dest = Square.of(inputs.get(MOVE_COMMAND_DESTINATION_INDEX));
            return new MoveCommand(outputView, chessBoard, src, dest);
        }
        if (commandType == CommandType.START) {
            return new StartCommand(outputView, chessBoard);
        }
        if (commandType == CommandType.STATUS) {
            return new StatusCommand(outputView, chessBoard);
        }
        return new EndCommand();
    }

}
