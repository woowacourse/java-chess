package chess.domain.game;

import static chess.domain.piece.Color.BLACK;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;

final class BlackTurn extends Running {

    private static final Color color = BLACK;
    private static final String INVALID_TURN_EXCEPTION_MESSAGE
            = String.format(INVALID_TURN_EXCEPTION_FORMAT, color);

    BlackTurn(ActivePieces chessmen) {
        super(chessmen);
    }

    @Override
    protected void validateTurn(Piece sourcePiece) {
        if (!sourcePiece.hasColorOf(color)) {
            throw new IllegalArgumentException(INVALID_TURN_EXCEPTION_MESSAGE);
        }
    }

    @Override
    protected Game continueGame() {
        return new WhiteTurn(chessmen);
    }

    @Override
    public String toString() {
        return "BlackTurn{" + "chessmen=" + chessmen + '}';
    }
}
