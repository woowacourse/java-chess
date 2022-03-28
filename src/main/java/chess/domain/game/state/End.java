package chess.domain.game.state;

import chess.domain.game.ChessGame;

public abstract class End extends State {
    protected static final String INVALID_COMMEND_MESSAGE = "게임이 종료되었습니다.";

    public End(ChessGame chessGame) {
        super(chessGame);
        this.run = false;
    }

    @Override
    protected State execute(String input) {
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }
}
