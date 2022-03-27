package chess.domain.game;

import static chess.domain.piece.Color.BLACK;

import chess.domain.piece.ActivePieces;
import chess.domain.piece.Color;

final class BlackTurn extends Running {

    BlackTurn(ActivePieces chessmen) {
        super(chessmen);
    }

    @Override
    protected Color currentTurnColor() {
        return BLACK;
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
