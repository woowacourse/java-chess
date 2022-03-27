package chess.domain.game;

public final class WhiteTurn extends Running {

    public WhiteTurn(ActivePieces chessmen) {
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
