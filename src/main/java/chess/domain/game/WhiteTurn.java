package chess.domain.game;

import chess.domain.piece.Position;

public class WhiteTurn extends Running {

    public WhiteTurn(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void move(final Position source, final Position target) {
        chessGame.getBoard().moveWhitePiece(source, target);
        chessGame.getBoard().catchBlackPiece();
        chessGame.changeState(new BlackTurn(chessGame));

        if (!chessGame.isKingCaught()) {
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
