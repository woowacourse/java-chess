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

    @Override
    public final FinishedState end() {
        return new FinishedState(players, color);
    }

    public abstract boolean isMovable();

    public abstract boolean isPromotable();

    public final Map<Color, Double> getPlayerScores(final ScoreCalculator scoreCalculator) {
        return players.calculatePlayerScores(scoreCalculator);
    }

    @Override
    public final Players getPlayers() {
        return players;
    }

    @Override
    public final Color getColor() {
        return color;
    }
}
