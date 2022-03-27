package chess.domain.game.stateLauncher;

import chess.controller.Command;
import chess.domain.game.ChessGame;

public final class Init extends StateLauncher {
    private static final String INVALID_COMMEND_MESSAGE = "end 혹은 start 만 입력할 수 있습니다.";

    public Init(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    protected StateLauncher execute(String input) {
        Command command = Command.from(input);
        if (command == Command.END) {
            return new End(chessGame);
        }
        if (command == Command.START) {
            chessGame.start();
            return new Play(chessGame);
        }
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }
}
