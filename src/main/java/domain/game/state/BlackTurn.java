package domain.game.state;

import domain.game.TeamColor;

public final class BlackTurn extends GamePlaying {
    @Override
    public TeamColor currentTurn() {
        return TeamColor.BLACK;
    }
}
