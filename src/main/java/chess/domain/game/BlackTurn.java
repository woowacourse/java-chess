package chess.domain.game;

import chess.domain.piece.Position;

public class BlackTurn extends Running {

    public BlackTurn(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void move(final Position source, final Position target) {
        chessGame.moveBlackPiece(source, target);
        chessGame.catchWhitePiece();
        chessGame.changeState(new WhiteTurn(chessGame));

        if (chessGame.isKingCaught()) {
            chessGame.changeState(new BlackWin(chessGame));
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
