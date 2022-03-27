package chess.controller.commendlauncher;

import chess.controller.Command;
import chess.domain.game.ChessGame;
import chess.view.OutputView;

public class End extends CommendLauncher {
    ChessGame chessGame;

    public End(ChessGame chessGame) {
        this.chessGame = chessGame;
        go(chessGame);
    }

    @Override
    protected void execute(ChessGame chessGame) {
        Command command = getCommand();
        if (command == Command.STATUS) {
            OutputView.printStatus(chessGame.getStatus());
            return;
        }
        throw new IllegalArgumentException("status 를 입력하세요.");
    }
}
