package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandType;
import chess.controller.command.StartCommand;
import chess.repository.BoardDao;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {

    private final OutputView outputView;
    private final InputView inputView;
    private Command command;

    public ChessController(OutputView outputView, InputView inputView, BoardDao boardDao) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.command = new StartCommand(boardDao);
    }

    public void run() {
        outputView.printStart();
        while (!command.isSameType(CommandType.END)) {
            playChessGame();
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
