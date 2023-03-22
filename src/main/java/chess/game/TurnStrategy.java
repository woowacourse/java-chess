package chess.game;

import chess.game.state.running.RunningState;

public interface TurnStrategy {
    RunningState create();
}
