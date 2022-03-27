package chess.domain.game;

import static chess.domain.piece.Color.WHITE;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;

final class WhiteTurn extends Running {

    private static final Color color = WHITE;
    private static final String INVALID_TURN_EXCEPTION_MESSAGE
            = String.format(INVALID_TURN_EXCEPTION_FORMAT, color);

    WhiteTurn(ActivePieces chessmen) {
        super(chessmen);
    }

    @Override
    protected void validateTurn(Piece sourcePiece) {
        if (!sourcePiece.hasColorOf(color)) {
            throw new IllegalArgumentException(INVALID_TURN_EXCEPTION_MESSAGE);
        }
    }

    protected Game continueGame() {
        return new BlackTurn(chessmen);
    }

    @Override
    public String toString() {
        return "WhiteTurn{" + "chessmen=" + chessmen + '}';
    }
}
