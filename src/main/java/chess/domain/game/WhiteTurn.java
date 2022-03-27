package chess.domain.game;

import static chess.domain.piece.Color.WHITE;

import chess.domain.piece.Color;

final class WhiteTurn extends Running {

    WhiteTurn(ActivePieces chessmen) {
        super(chessmen);
    }

    @Override
    protected Color currentTurnColor() {
        return WHITE;
    }

    @Override
    protected Game continueGame() {
        return new BlackTurn(chessmen);
    }

    @Override
    public String toString() {
        return "WhiteTurn{" + "chessmen=" + chessmen + '}';
    }
}
