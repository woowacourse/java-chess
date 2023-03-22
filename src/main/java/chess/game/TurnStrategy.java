package chess.game;

import chess.game.state.RunningState;

public interface TurnStrategy {
    RunningState create();
}
