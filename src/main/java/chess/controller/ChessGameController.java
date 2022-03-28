package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        ChessGame chessGame = new ChessGame();

        while (!chessGame.isEnd()) {
            final String rawInputCommand = inputView.inputCommand();
            final Command command = ChessGameCommand.of(rawInputCommand, chessGame);
            command.execute(rawInputCommand, outputView);
        }
    }
}
