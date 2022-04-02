package chess.dao;

import chess.domain.state.StateGenerator;

public class MemoryTurnDaoImpl implements TurnDao {

    private static StateGenerator stateGenerator;

    @Override
    public void save(String state) {
        this.stateGenerator = StateGenerator.of(state);
    }

    @Override
    public StateGenerator findLastSaved() {
        return stateGenerator;
    }
}
