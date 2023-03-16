package chess.controller;

import chess.domain.chess.ChessGame;
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
        final ChessGame chessGame = new ChessGame();
        final Command command = getCommand();
        if (command == Command.START) {
            outputView.printBoard(chessGame.getChessBoard());
        }
    }

    private Command getCommand() {
        outputView.printStartMessage();
        List<String> commands = inputView.getCommand();
        return Command.findCommand(commands.get(0));
    }
}
