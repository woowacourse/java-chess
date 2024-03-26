package domain.game.state;

import domain.game.TeamColor;

public final class BlackTurn extends GamePlaying {
    private static final BlackTurn instance = new BlackTurn();

    private BlackTurn() {
    }

    public static BlackTurn getInstance() {
        return instance;
    }

    @Override
    public TeamColor currentTurn() {
        return TeamColor.BLACK;
    }
}
