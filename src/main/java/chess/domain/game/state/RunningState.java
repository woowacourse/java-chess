package chess.domain.game.state;

import java.util.Map;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.game.ScoreCalculator;
import chess.domain.player.PlayerFactory;
import chess.domain.player.Players;

public class RunningState implements GameState {

    protected final Players players;
    protected final Color color;

    public RunningState(final Players players, final Color color) {
        this.players = players;
        this.color = color;
    }

    public static RunningState createFirstTurnRunning() {
        final Players players = Players.initialize(PlayerFactory.getInstance());
        return new RunningState(players, Color.WHITE);
    }

    @Override
    public final boolean isRunning() {
        return true;
    }

    public GameState move(final Position source, final Position target) {
        validateGameNotPromotionState();
        players.move(color, source, target);
        return considerGameState();
    }

    private void validateGameNotPromotionState() {
        if (isPromotable()) {
            throw new IllegalStateException("프로모션을 진행해야 합니다.");
        }
    }

    private GameState considerGameState() {
        if (players.isOnlyOneKingLeft()) {
            return new FinishedState(players, color);
        }
        if (players.isPlayerAbleToPromotePawn(color)) {
            return this;
        }
        return new RunningState(players, color.reverse());
    }

    public GameState promotion(final String pieceName) {
        players.promotePawn(color, pieceName);
        return new RunningState(players, color.reverse());
    }

    @Override
    public final FinishedState end() {
        return new FinishedState(players, color);
    }

    public boolean isMovable() {
        return true;
    }

    public boolean isPromotable() {
        return players.isPlayerAbleToPromotePawn(color);
    }

    @Override
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
