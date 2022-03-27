package chess.domain.game.stateLauncher;

import chess.domain.game.ChessGame;

public final class End extends StateLauncher {
    private static final String INVALID_COMMEND_MESSAGE = "게임이 종료되었습니다.";

    public End(ChessGame chessGame) {
        super(chessGame);
        this.run = false;
    }

    @Override
    protected StateLauncher execute(String input) {
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }
}
