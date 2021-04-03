package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Position;

public class WhiteTurn extends Running {
    private static final Color COLOR = Color.WHITE;

    public WhiteTurn(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void move(final Position source, final Position target) {
        chessGame.getBoard()
                .moveAndCatchPiece(COLOR, source, target);

        if(chessGame.isKingCatch()) {
            chessGame.changeState(new End(chessGame));
            return;
        }
        chessGame.changeState(new BlackTurn(chessGame));
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
    public String toString() {
        return "화이트팀 턴 입니다.";
    }
}
