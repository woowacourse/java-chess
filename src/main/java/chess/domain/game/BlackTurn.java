package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Position;

public class BlackTurn extends Running {

    public BlackTurn(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void move(final Position source, final Position target) {
        Color black = Color.BLACK;
        chessGame.getBoard().movePiece(black, source, target);
        chessGame.getBoard().catchPiece(black);
        chessGame.changeState(new WhiteTurn(chessGame));

        if (!chessGame.isKingsExist()) {
            chessGame.changeState(new End(chessGame));
        }
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
