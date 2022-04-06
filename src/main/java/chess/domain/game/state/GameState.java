package chess.domain.game.state;

import chess.domain.Color;
import chess.domain.player.Players;

public interface GameState {

    boolean isRunning();

    GameState end();

    Players getPlayers();

    Color getColor();
}
