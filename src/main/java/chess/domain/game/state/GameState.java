package chess.domain.game.state;

import chess.domain.player.Players;

public interface GameState {

    boolean isRunning();

    Players getPlayers();
}
