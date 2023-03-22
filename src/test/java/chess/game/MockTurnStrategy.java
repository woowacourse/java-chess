package chess.game;

import chess.game.state.RunningState;

public class MockTurnStrategy implements TurnStrategy {

    private final RunningState mockTurn;

    public MockTurnStrategy(RunningState runningState) {
        this.mockTurn = runningState;
    }

    @Override
    public RunningState create() {
        return mockTurn;
    }
}
