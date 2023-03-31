package controller;

import controller.command.ControlCommand;
import domain.ChessGame;
import java.util.Arrays;
import java.util.List;
import view.CommandType;
import view.InputView;
import view.OutputView;

public final class ChessController {

    private static final int MAIN_COMMAND_INDEX = 0;
    private static final int GAME_COMMAND_INDEX = 0;
    private static final String COMMANDS_DELIMITER = " ";
    private final ChessGame chessGame;
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();

    public ChessController(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void ready() {
        execute(this::getGameStartCommand);
    }

    private void execute(final Runnable runningFunction) {
        try {
            runningFunction.run();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            execute(runningFunction);
        }
    }

    private void getGameStartCommand() {
        final CommandType commandType = CommandType.find(inputView.getStartCommand().strip());
        final ControlCommand command = ControlCommand.of(commandType, chessGame);
        reactControlCommand(command);
    }

    public void play() {
        while (chessGame.isReady()) {
            execute(() -> reactCommand(getPlayCommands()));
        }
        outputView.printEndMessage();
    }

    private List<String> getPlayCommands() {
        final List<String> inputCommands = Arrays.asList(inputView.getGameCommand().split(COMMANDS_DELIMITER, -1));
        if (CommandType.notExist(inputCommands.get(MAIN_COMMAND_INDEX))) {
            throw new IllegalArgumentException("잘못된 커맨드 입력입니다.");
        }
        return inputCommands;
    }

    private void reactCommand(final List<String> commandInputs) {
        final String commandInput = commandInputs.get(GAME_COMMAND_INDEX);
        final CommandType commandType = CommandType.find(commandInput);
        if (commandType == CommandType.MOVE || commandType == CommandType.STATUS) {
            reactPlayCommand(commandType, commandInputs);
            return;
        }
        final ControlCommand controlCommand = ControlCommand.of(commandType, chessGame);
        reactControlCommand(controlCommand);
    }

    private void reactControlCommand(final ControlCommand command) {
        command.run();
        printBoard();
    }

    private void reactPlayCommand(final CommandType commandType, final List<String> commandInputs) {
        if (commandType == CommandType.STATUS) {
            printScore();
            return;
        }
        chessGame.move(Move.of(commandInputs));
        printBoard();
    }

    private void printBoard() {
        if (chessGame.isReady()) {
            outputView.printBoard(chessGame.getBoard());
        }
    }

    private void printScore() {
        outputView.printScores(chessGame.getBlackScore(), chessGame.getWhiteScore());
    }
}
