package chess.domain.game;

import chess.domain.piece.Position;

public class End extends Idle {
    private static final String END_STATE_IDENTITY_VALUE = "End";

    public End(final ChessGame chessGame) {
        super(chessGame, END_STATE_IDENTITY_VALUE);
    }

    @Override
    public void move(final Position source, final Position target) {
        throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED);
    }

    @Override
    public void start() {
        throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State nextTurn() {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }

}
