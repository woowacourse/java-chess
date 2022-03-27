package chess.domain.game;

final class WhiteTurn extends Running {

    WhiteTurn(ActivePieces chessmen) {
        super(chessmen);
    }

    protected Game continueGame() {
        return new BlackTurn(chessmen);
    }

    @Override
    public String toString() {
        return "WhiteTurn{" + "chessmen=" + chessmen + '}';
    }
}
