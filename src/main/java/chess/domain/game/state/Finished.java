package chess.domain.game.state;

import chess.domain.game.ChessGame;
import chess.dto.CommandDto;

public abstract class Finished extends AbstractState {
    protected static final String INVALID_COMMEND_MESSAGE = "게임이 종료되었습니다.";

    public Finished(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public AbstractState execute(CommandDto commandDto) {
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }

    public boolean isRun() {
        return false;
    }
}
