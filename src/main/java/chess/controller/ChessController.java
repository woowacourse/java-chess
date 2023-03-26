package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandType;
import chess.controller.command.StartCommand;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;
    
    private final OutputView outputView;
    private final InputView inputView;
    private Command command;

    public ChessController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.command = new StartCommand();
    }

    public void run() {
        outputView.printStart();
        while (!command.isSameType(CommandType.END)) {
            playChessGame();
            outputView.printBoard(command.getChessGameBoards());
        }
    }

    private void playChessGame() {
        try {
            List<String> input = readInput();
            command = command.execute(input);
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            outputView.printError(e.getMessage());
            playChessGame();
        }
    }

    private List<String> readInput() {
        try {
            return inputView.readGameCommand();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return readInput();
        }
    }
}
