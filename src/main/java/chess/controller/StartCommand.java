package chess.controller;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class StartCommand implements Command {
    @Override
    public void execute(ChessGame chessGame, OutputView outputView) {
        outputView.printStartMessage();
    }

    @Override
    public boolean isNotEndCommand() {
        return true;
    }
}
