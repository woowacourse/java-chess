package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Position;

public class WhiteTurn extends Running {

    public WhiteTurn(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void move(final Position source, final Position target) {
        chessGame.moveAndCatchPiece(Color.WHITE, source, target);
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
        return new BlackTurn(chessGame);
    }

}
