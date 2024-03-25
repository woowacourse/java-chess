package chess.controller;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class EndCommand implements Command {
    @Override
    public void execute(ChessGame chessGame, OutputView outputView) {
    }

    @Override
    public boolean isNotEndCommand() {
        return false;
    }
}
