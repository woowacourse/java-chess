package chess.domain.game;

import chess.domain.piece.Position;

public class Ready extends Idle {

    public Ready(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void move(final Position source, final Position target) {
        throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED);
    }

    @Override
    public void start() {
        chessGame.changeState(new BlackTurn(chessGame));
    }

}
