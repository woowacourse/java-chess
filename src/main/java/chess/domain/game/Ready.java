package chess.domain.game;

import chess.domain.piece.Position;

public class Ready extends Idle {
    private static final String READY_STATE_IDENTITY_VALUE = "Ready";

    public Ready(final ChessGame chessGame) {
        super(chessGame, READY_STATE_IDENTITY_VALUE);
    }

    @Override
    public void move(final Position source, final Position target) {
        throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED);
    }

    @Override
    public void start() {
        chessGame.changeState(new BlackTurn(chessGame));
    }

    @Override
    public State nextTurn() {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }

}
