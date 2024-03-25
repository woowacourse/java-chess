package chess.controller.command;

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

    @Override
    public boolean isNotStartCommand() {
        return true;
    }
}
