package chess.controller;

import chess.service.ChessGame;
import chess.view.OutputView;

public class EndCommand implements Command {
    private final OutputView outputView;
    private final ChessGame chessGame;

    public EndCommand(OutputView outputView, ChessGame chessGame) {
        this.outputView = outputView;
        this.chessGame = chessGame;
    }

    @Override
    public void execute(String command) {
        chessGame.forceClose();
        outputView.printEndMessage();
    }
}
