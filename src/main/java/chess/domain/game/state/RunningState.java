package chess.domain.game.state;

import java.util.Map;

import chess.domain.Color;
import chess.domain.game.ScoreCalculator;
import chess.domain.player.Players;

public abstract class RunningState implements GameState {

    protected final Players players;
    protected final Color color;

    protected RunningState(final Players players, final Color color) {
        this.players = players;
        this.color = color;
    }

    @Override
    public final boolean isRunning() {
        return true;
    }

    public abstract boolean isMovable();

    public abstract boolean isPromotable();

    public final Map<Color, Double> getPlayerScores(final ScoreCalculator scoreCalculator) {
        return players.calculatePlayerScores(scoreCalculator);
    }

    public final Players getPlayers() {
        return players;
    }
}
