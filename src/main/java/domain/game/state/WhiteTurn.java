package domain.game.state;

import domain.game.TeamColor;

public final class WhiteTurn extends GamePlaying {
    @Override
    public TeamColor currentTurn() {
        return TeamColor.WHITE;
    }
}
