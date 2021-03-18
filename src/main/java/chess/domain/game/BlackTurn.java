package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Position;

public class BlackTurn extends Running {
    private static final Color COLOR = Color.BLACK;

    public BlackTurn(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void move(final Position source, final Position target) {
        chessGame.getBoard().movePiece(COLOR, source, target);
        chessGame.changeState(new WhiteTurn(chessGame));
    }

    @Override
    public void start() {
        throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED);
    }

    @Override
    public void end() {
        chessGame.changeState(new End(chessGame));
    }
}
