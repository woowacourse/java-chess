package chess.domain.game.state;

import java.util.Map;

import chess.domain.Color;
import chess.domain.game.ScoreCalculator;
import chess.domain.player.Players;

public interface GameState {

    boolean isRunning();

    GameState end();

    Map<Color, Double> getPlayerScores(final ScoreCalculator scoreCalculator);

    Players getPlayers();

    Color getColor();
}
