package chess.controller.command;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class StartCommand implements Command {
    @Override
    public void execute(ChessGame chessGame, OutputView outputView) {
        outputView.printChessBoardMessage(chessGame.getChessBoard());
    }

    @Override
    public boolean isNotEndCommand() {
        return true;
    }

    @Override
    public boolean isNotStartCommand() {
        return false;
    }
}
