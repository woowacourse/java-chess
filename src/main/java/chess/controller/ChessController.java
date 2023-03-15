package chess.controller;

import chess.domain.board.ChessBoard;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Command command = getCommand();
        if (command == Command.START) {
            final ChessBoard chessBoard = new ChessBoard();
            outputView.printBoard(chessBoard.getBoard());
        }
    }

    private Command getCommand() {
        outputView.printStartMessage();
        return inputView.getInputWithRetry(() -> {
            List<String> commands = inputView.getCommand();
            return Command.of(commands.get(0));
        });
    }
}
