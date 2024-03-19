package chess.controller;

import chess.domain.BoardFactory;
import chess.domain.ChessGame;
import chess.domain.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessGame game = new ChessGame(BoardFactory.create());
        outputView.printGameStart();

        while (Command.END != Command.from(inputView.readCommand())) {
            outputView.printBoard(game.getBoard());
        }
    }
}
