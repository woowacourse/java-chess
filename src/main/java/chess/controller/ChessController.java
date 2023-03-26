package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PositionMapper;

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
            outputView.printBoard(command.getChessGame().getBoard());
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
            List<String> input = inputView.readGameCommand();
            return input;
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return readInput();
        }
    }
}
