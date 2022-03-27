package chess.domain.game.stateLauncher;

import chess.controller.Command;
import chess.domain.game.ChessGame;

public final class KingGo extends StateLauncher {
    private static final String INVALID_COMMEND_MESSAGE = "status 를 입력하세요.";

    public KingGo(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    protected StateLauncher execute(String input) {
        Command command = Command.from(input);
        if (command == Command.STATUS) {
            return new Status(chessGame);
        }
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }
}
