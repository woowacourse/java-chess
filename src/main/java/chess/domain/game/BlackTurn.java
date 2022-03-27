package chess.domain.game;

public final class BlackTurn extends Running {

    public BlackTurn(ActivePieces chessmen) {
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
