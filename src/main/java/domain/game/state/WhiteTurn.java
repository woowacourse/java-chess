package domain.game.state;

import domain.game.TeamColor;

public final class WhiteTurn extends GamePlaying {
    private static final WhiteTurn instance = new WhiteTurn();

    private WhiteTurn() {
    }

    public static WhiteTurn getInstance() {
        return instance;
    }

    @Override
    public TeamColor currentTurn() {
        return TeamColor.WHITE;
    }
}
