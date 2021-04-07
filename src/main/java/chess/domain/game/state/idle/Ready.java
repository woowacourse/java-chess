package chess.domain.game.state.idle;

import chess.domain.game.ChessGame;
import chess.domain.game.state.running.WhiteTurn;
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
        chessGame.changeState(new WhiteTurn(chessGame));
    }

    @Override
    public void end() {
        throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED);
    }

}
