package chess.domain.game;

final class BlackTurn extends Running {

    BlackTurn(ActivePieces chessmen) {
        super(chessmen);
    }

    protected Game continueGame() {
        return new WhiteTurn(chessmen);
    }

    @Override
    public String toString() {
        return "BlackTurn{" + "chessmen=" + chessmen + '}';
    }
}
