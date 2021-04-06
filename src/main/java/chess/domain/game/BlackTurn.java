package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Position;

public class BlackTurn extends Running {
    private static final String BLACK_TURN_STATE_IDENTITY_VALUE = "BlackTurn";

    public BlackTurn(final ChessGame chessGame) {
        super(chessGame, BLACK_TURN_STATE_IDENTITY_VALUE);
    }

    @Override
    public void move(final Position source, final Position target) {
        chessGame.moveAndCatchPiece(Color.BLACK, source, target);
    }

    @Override
    public void start() {
        throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED);
    }

    @Override
    public void end() {
        chessGame.changeState(new End(chessGame));
    }

    @Override
    public State nextTurn() {
        return new WhiteTurn(chessGame);
    }

}
