package chess.domain.game.state;

import chess.domain.player.Players;

public final class FinishedState implements GameState {

    private final Players players;

    public FinishedState(final Players players) {
        this.players = players;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public Players getPlayers() {
        return players;
    }
}
