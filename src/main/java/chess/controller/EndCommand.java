package chess.controller;

import chess.service.ChessGame;
import chess.view.OutputView;

public class EndCommand implements Command {
    private final OutputView outputView;
    private final ChessGame chessGame;

    public EndCommand(final OutputView outputView, final ChessGame chessGame) {
        this.outputView = outputView;
        this.chessGame = chessGame;
    }

    @Override
    public void execute(final String command) {
        chessGame.forceClose();
        outputView.printEndMessage();
    }
}
