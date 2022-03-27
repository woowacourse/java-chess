package chess.controller.commendlauncher;

import chess.controller.Command;
import chess.domain.game.ChessGame;
import chess.view.OutputView;

public final class End extends ChessController {
    private static final String INVALID_COMMEND_MESSAGE = "status 를 입력하세요.";

    public End(ChessGame chessGame) {
        go(chessGame);
    }

    @Override
    protected void execute(ChessGame chessGame) {
        Command command = getCommand();
        if (command == Command.STATUS) {
            OutputView.printStatus(chessGame.getStatus());
            return;
        }
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }

    @Override
    public void start() {}
}
