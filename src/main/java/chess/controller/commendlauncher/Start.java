package chess.controller.commendlauncher;

import chess.controller.Command;
import chess.domain.game.ChessGame;

public final class Start extends CommendLauncher {


    private static final String INVALID_COMMEND_MESSAGE = "end 혹은 start 만 입력할 수 있습니다.";

    public Start() {
        go(new ChessGame());
    }

    @Override
    protected void execute(ChessGame chessGame) {
        Command command = getCommand();
        if (command == Command.END) {
            new End(chessGame);
            return;
        }
        if (command == Command.START) {
            chessGame.start();
            new Play(chessGame);
            return;
        }
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }
}
